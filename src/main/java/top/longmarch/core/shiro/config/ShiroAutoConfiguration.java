package top.longmarch.core.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import top.longmarch.core.common.Constant;
import top.longmarch.core.shiro.cache.CacheManagerBuilder;
import top.longmarch.core.shiro.filter.KickoutSessionControlFilter;
import top.longmarch.core.shiro.filter.LMFormAuthenticationFilter;
import top.longmarch.core.shiro.filter.LMPathMatchingFilter;
import top.longmarch.core.shiro.listener.LongmarchSessionListener;
import top.longmarch.core.shiro.realm.CustomRealm;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroAutoConfiguration {

    @Bean(name = "LMCacheManager")
    public CacheManager cacheManager(ObjectProvider<org.springframework.cache.CacheManager> springCacheManagerPvd,
                                     RedisConnectionFactory redisConnectionFactory) {
        CacheManagerBuilder cacheManagerBuilder = new CacheManagerBuilder(springCacheManagerPvd, redisConnectionFactory);
        return cacheManagerBuilder.build();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, CacheManager cacheManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("cross", new LMPathMatchingFilter());
        filterMap.put("authc", new LMFormAuthenticationFilter());
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setCacheManager(cacheManager);
        kickoutSessionControlFilter.setSessionManager(sessionManager(cacheManager));
        filterMap.put("Kickout", kickoutSessionControlFilter);
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/noLogin");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/douyin/callback", "anon");
        filterChainDefinitionMap.put("/mavon-editor.js", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "cross,anon");
        //对外暴露API接口
        filterChainDefinitionMap.put("/api/**", "anon");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        filterChainDefinitionMap.put("/**", "cross,Kickout,authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(CacheManager cacheManager) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        defaultSecurityManager.setCacheManager(cacheManager);
        defaultSecurityManager.setSessionManager(sessionManager(cacheManager));
        defaultSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultSecurityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        SimpleCookie simpleCookie = new SimpleCookie("long-march");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        sessionManager.setSessionIdCookie(simpleCookie);

        sessionManager.setGlobalSessionTimeout(Constant.SESSION_TIMEOUT);
        // Session Listeners 负责清理缓存中的用户信息
        sessionManager.setSessionListeners(Arrays.asList(new LongmarchSessionListener()));
        sessionManager.setCacheManager(cacheManager);
        // 会话过期时是否删除过期的会话
        sessionManager.setDeleteInvalidSessions(true);
        // 是否开启会话验证器
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 设置会话验证调度器
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setSessionManager(sessionManager);
        scheduler.setInterval(Constant.SESSION_CLEAR_TILE);
        // 执行会话验证调度器
        scheduler.enableSessionValidation();
        sessionManager.setSessionValidationScheduler(scheduler);
        sessionManager.setSessionDAO(new LMSessionDAO(cacheManager));
        return sessionManager;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("remember-long-march");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        rememberMeManager.setCookie(simpleCookie);
        byte[] decode = Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA==");
        rememberMeManager.setCipherKey(decode);
        return rememberMeManager;
    }

    @Bean
    public CustomRealm customRealm() {
        CustomRealm customRealm = new CustomRealm();
        // 告诉realm,使用credentialsMatcher加密算法类来验证密文
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        customRealm.setCachingEnabled(true);
//        customRealm.setCacheManager(ehCacheManager());
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        customRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        customRealm.setAuthenticationCacheName(Constant.AUTHENTICATION_CACHE);
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        customRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        customRealm.setAuthorizationCacheName(Constant.AUTHORIZATION_CACHE);
        return customRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * *
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * *
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
