package top.longmarch.core.shiro.service;

import java.util.Map;

public interface UserIRolePermissionCacheService {

    Map<String, Object> getActivityUserInfo(Long userId);

    void cleanActivityUserInfo(Long userId);

}
