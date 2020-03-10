package top.longmarch.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;
import top.longmarch.core.shiro.cache.LMRedisCacheManager;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Service
public class LMCacheManage {


    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SessionManager sessionManager;

    public int getOnlineUsersCount() {
        return cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE).size();
    }

    public void cleanActivityUserInfo(Long userId) {
        cacheManager.getCache(Constant.USER_PERMISSION_CACHE).remove(String.format(Constant.ACTIVITY_USER_INFO_KEY, userId));
    }

    public void removeOnlineUser(String username) {
        Cache cache = cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE);
        Cache<Object, Object> sessionCache = cacheManager.getCache(Constant.ACTIVE_SESSION_CACHE);
        String sessionId = JSONUtil.parseObj(cache.get(username)).getStr("last");
        if (StrUtil.isNotBlank(sessionId)) {
            DefaultSessionKey serializable = new DefaultSessionKey(sessionId);
            Session session = sessionManager.getSession(serializable);
            session.setTimeout(0);
            cache.remove(username);
            sessionCache.remove(serializable);
        }
    }

    public void cleanCacheSession(String sessionId) {
        Cache cache = cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE);
        Cache<Object, Object> sessionCache = cacheManager.getCache(Constant.ACTIVE_SESSION_CACHE);
        Set keys = cache.keys();
        for (Object key : keys) {
            String _sessionId = JSONUtil.parseObj(cache.get(key)).getStr("last");
            if (sessionId.equals(_sessionId)) {
                cache.remove(key);
                sessionCache.remove(key);
                break;
            }
        }
    }

    public List<Object> getOnlineUsers() {
        List<Object> onlineUsers = new ArrayList<>();
        Cache cache = cacheManager.getCache(Constant.KEEP_ONE_USER_CACHE);
        for (Object key : cache.keys()) {
            String sessionId = JSONUtil.parseObj(cache.get(key)).getStr("last");
            if (StrUtil.isBlank(sessionId)) {
                cleanCacheSession(sessionId);
            } else {
                Map<String, Object> user = new HashMap<>();
                Session session = sessionManager.getSession(new DefaultSessionKey(sessionId));
                user.put("username", key);
                user.put("sessionId", session.getId());
                user.put("host", session.getHost());
                user.put("startTimestamp", session.getStartTimestamp());
                user.put("lastAccessTime", session.getLastAccessTime());
                user.put("timeout", TimeUnit.MILLISECONDS.toHours(session.getTimeout()));
                onlineUsers.add(user);
            }
        }
        return onlineUsers;
    }

    public List<Object> getAllCache() {
        List<Object> cacheManageList = new ArrayList<>();
        if (cacheManager instanceof LMRedisCacheManager) {
            LMRedisCacheManager redisCacheManager = (LMRedisCacheManager) cacheManager;
            ConcurrentMap<String, Cache> caches = redisCacheManager.getCaches();
            for (String cacheName : caches.keySet()) {
                Cache cache = caches.get(cacheName);
                for (Object key : cache.keys()) {
                    Map<String, Object> cacheMap = new HashMap<>();
                    cacheMap.put("key", cacheName + key);
                    cacheMap.put("value", cache.get(key));
                    cacheManageList.add(cacheMap);
                }
            }
        } else if (cacheManager instanceof EhCacheManager) {
            EhCacheManager ehCacheManager = (EhCacheManager) cacheManager;
            net.sf.ehcache.CacheManager cacheManager = ehCacheManager.getCacheManager();
            String[] cacheNames = cacheManager.getCacheNames();
            for (String cacheName : cacheNames) {
                net.sf.ehcache.Cache cache = cacheManager.getCache(cacheName);
                for (Object key : cache.getKeys()) {
                    Map<String, Object> cacheMap = new HashMap<>();
                    cacheMap.put("key", cacheName + key);
                    cacheMap.put("value", cache.get(key));
                    cacheManageList.add(cacheMap);
                }
            }
        }
        return cacheManageList;
    }

}
