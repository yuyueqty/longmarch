package top.longmarch.core.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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

//    @Bean
//    public CacheManager cacheManager() {
//        MemoryConstrainedCacheManager memoryConstrainedCacheManager = new MemoryConstrainedCacheManager();
//        return memoryConstrainedCacheManager;
//    }

    @Bean(name = "ehCacheManager")
    public CacheManager ehCacheManager(net.sf.ehcache.CacheManager cacheManager) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        //将ehcacheManager转换成shiro包装后的ehcacheManager对象
        ehCacheManager.setCacheManager(cacheManager);
//        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
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
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/doc.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "cross,anon");
        //对外暴露API接口
        filterChainDefinitionMap.put("/api/**", "cross,anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/font-awesome/**", "anon");
        filterChainDefinitionMap.put("/highlight/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/jquery/**", "anon");
        filterChainDefinitionMap.put("/temporary-img/**", "anon");
        filterChainDefinitionMap.put("/view/**", "anon");
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

//    public SessionDAO sessionDAO() {
//        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
//
//        return enterpriseCacheSessionDAO;
//    }

    @Bean
    public DefaultWebSessionManager sessionManager(CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        SimpleCookie simpleCookie = new SimpleCookie("long-march");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        sessionManager.setSessionIdCookie(simpleCookie);
        // 默认SESSION超时时间：1小时=3600000毫秒(ms)
        long timeout = 1000 * 60 * 60 * 24;
        sessionManager.setGlobalSessionTimeout(timeout);
        sessionManager.setSessionListeners(Arrays.asList(new LongmarchSessionListener()));
        sessionManager.setCacheManager(cacheManager);
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
        customRealm.setAuthenticationCacheName("__authenticationCache__");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        customRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        customRealm.setAuthorizationCacheName("__authorizationCache__");
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
