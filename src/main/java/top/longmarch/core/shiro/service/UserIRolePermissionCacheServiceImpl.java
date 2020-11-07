package top.longmarch.core.shiro.service;

import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;
import top.longmarch.sys.service.impl.LMCacheManage;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserIRolePermissionCacheServiceImpl implements UserIRolePermissionCacheService {

    private static final Logger log = LoggerFactory.getLogger(UserIRolePermissionCacheServiceImpl.class);
    @Autowired
    private UserIRolePermissionService userIRolePermissionService;
    @Autowired
    private LMCacheManage lmCacheManage;

    @Override
    public Map<String, Object> getActivityUserInfo(Long userId) {
        Map<String, Object> activityUserInfo;
        Cache cache = lmCacheManage.getCache(Constant.USER_PERMISSION_CACHE);
        String key = String.format(Constant.ACTIVITY_USER_INFO_KEY, userId);
        Object o = cache.get(key);
        if (o != null) {
            activityUserInfo = ((Map<String, Object>) o);
        } else {
            activityUserInfo = userIRolePermissionService.getActivityUserInfo(userId);
            cache.put(key, activityUserInfo);
        }
        return activityUserInfo;
    }

}
