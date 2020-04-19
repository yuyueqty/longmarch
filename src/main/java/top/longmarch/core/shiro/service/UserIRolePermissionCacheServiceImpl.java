package top.longmarch.core.shiro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

@CacheConfig(cacheNames = {"UserIRolePermissionCacheService"})
@Service
public class UserIRolePermissionCacheServiceImpl implements UserIRolePermissionCacheService {

    private static final Logger log = LoggerFactory.getLogger(UserIRolePermissionCacheServiceImpl.class);
    @Autowired
    private UserIRolePermissionService userIRolePermissionService;

//    @Cacheable(key = "'activity_user_info_' + #userId")
    @Override
    public Map<String, Object> getActivityUserInfo(Long userId) {
        return userIRolePermissionService.getActivityUserInfo(userId);
    }

}
