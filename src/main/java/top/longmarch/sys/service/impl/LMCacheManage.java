package top.longmarch.sys.service.impl;

import cn.hutool.json.JSONUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LMCacheManage {


    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SessionManager sessionManager;

    public int getOnlineUsersCount() {
        return cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE).getSize();
    }

    public void cleanActivityUserInfo(Long userId) {
        cacheManager.getCache(Constant.USER_PERMISSION_CACHE).remove(String.format(Constant.ACTIVITY_USER_INFO_KEY, userId));
    }

    public void removeOnlineUser(String username) {
        Cache cache = cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE);
        String sessionId = JSONUtil.parseObj(cache.get(username).getValue()).getStr("last");
        DefaultSessionKey serializable = new DefaultSessionKey(sessionId);
        Session session = sessionManager.getSession(serializable);
        session.setTimeout(0);
        cache.remove(username);
    }

    public List<Object> getOnlineUsers() {
        List<Object> onlineUsers = new ArrayList<>();
        Cache cache = cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE);
        List keys = cache.getKeys();
        for (Object key : keys) {
            Map<String, Object> user = new HashMap<>();
            String sessionId = JSONUtil.parseObj(cache.get(key).getValue()).getStr("last");
            Session session = sessionManager.getSession(new DefaultSessionKey(sessionId));
            user.put("username", key);
            user.put("sessionId", session.getId());
            user.put("host", session.getHost());
            user.put("startTimestamp", session.getStartTimestamp());
            user.put("lastAccessTime", session.getLastAccessTime());
            user.put("timeout", TimeUnit.MILLISECONDS.toHours(session.getTimeout()));
            onlineUsers.add(user);
        }
        return onlineUsers;
    }

    public List<Object> getAllCache() {
        List<Object> cacheManageList = new ArrayList<>();
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            Map<String, Object> cacheManageMap = new HashMap<>();
            cacheManageMap.put("cacheName", cacheName);
            List<Map<String, Object>> cacheList = new ArrayList<>();
            Cache cache = cacheManager.getCache(cacheName);
            List keys = cache.getKeys();
            for (Object key : keys) {
                Map<String, Object> cacheMap = new HashMap<>();
                cacheMap.put("key", key);
                cacheMap.put("value", cache.get(key));
                cacheList.add(cacheMap);
            }
            cacheManageMap.put("cache", cacheList);
            cacheManageList.add(cacheManageMap);
        }
        return cacheManageList;
    }

}
