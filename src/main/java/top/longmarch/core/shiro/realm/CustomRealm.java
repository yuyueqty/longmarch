package top.longmarch.core.shiro.realm;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import top.longmarch.core.enums.StatusEnum;
import top.longmarch.core.shiro.service.UserIRolePermissionService;
import top.longmarch.sys.entity.User;

import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(CustomRealm.class);
    @Autowired
    private UserIRolePermissionService userIRolePermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        logger.info("校验用户{}权限", user.getUsername());
        Set<String> buttonPermissionStringSet = userIRolePermissionService.getUserPermissionByUserId(user.getId());
        simpleAuthorizationInfo.setStringPermissions(buttonPermissionStringSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始进行身份认证");
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new AccountException();
        }
        User user = userIRolePermissionService.getUserByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (StatusEnum.NO.getValue() == user.getStatus()) {
            throw new LockedAccountException();
        }
        Map<String, Object> map = userIRolePermissionService.getRoleDeptIdsByUserId(user.getId());
        if (CollectionUtil.isEmpty(map)) {
            user.setType(1);
        } else {
            user.setType(Integer.valueOf(map.get("type").toString()));
            user.setUserIdSet((Set) map.get("userIdSet"));
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    @Override
    protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return upToken.getUsername();
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        return user.getUsername();
    }

    @Override
    protected void doClearCache(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        String username = user.getUsername();
        Cache<Object, Object> authenticationCache = getCache(getAuthenticationCacheName());
        if (authenticationCache != null) {
            authenticationCache.remove(username);
            logger.info("用户[{}]认证信息已被清除！", username);
        }
        clearCache(username);
    }

    public void clearCache(String username) {
        Cache<Object, Object> authorizationCache = getCache(getAuthorizationCacheName());
        if (authorizationCache != null) {
            authorizationCache.remove(username);
            logger.info("用户[{}]权限信息已被清除！", username);
        }
    }

    public void clearCache() {
        Cache<Object, Object> authorizationCache = getCache(getAuthorizationCacheName());
        if (authorizationCache != null) {
            authorizationCache.clear();
            logger.info("所有用户权限信息已被清除！");
        }
    }

    public Cache<Object, Object> getCache(String cacheName) {
        CacheManager cacheManager = getCacheManager();
        return cacheManager.getCache(cacheName);
    }

}
