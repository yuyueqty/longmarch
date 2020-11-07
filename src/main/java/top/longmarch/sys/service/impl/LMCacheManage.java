package top.longmarch.sys.service.impl;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;
import top.longmarch.core.shiro.cache.LMRedisCacheManager;
import top.longmarch.sys.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LMCacheManage {


    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SessionManager sessionManager;

    public int getOnlineUsersCount() {
        return getCache(Constant.KEEP_ONE_USER_CACHE).size();
    }

    public void cleanActivityUserInfo(Long userId) {
        Cache cache = getCache(Constant.USER_PERMISSION_CACHE);
        if (cache != null) {
            cache.remove(String.format(Constant.ACTIVITY_USER_INFO_KEY, userId));
        }
    }

    public void cleanCacheSession(String sessionId) {
        Cache active_session_cache = getCache(Constant.ACTIVE_SESSION_CACHE);
        if (active_session_cache != null) {
            Object o = active_session_cache.get(sessionId);
            if (o != null) {
                SimpleSession simpleSession = JSONUtil.toBean(JSONUtil.parseObj(o), SimpleSession.class);
                User user = getUser(simpleSession);
                active_session_cache.remove(sessionId);
                Cache keep_one_user_cache = getCache(Constant.KEEP_ONE_USER_CACHE);
                if (keep_one_user_cache != null) {
                    keep_one_user_cache.remove(user.getUsername());
                }
                Cache authentication_cache = getCache(Constant.AUTHENTICATION_CACHE);
                if (authentication_cache != null) {
                    authentication_cache.remove(user.getUsername());
                }
                Cache authorization_cache = getCache(Constant.AUTHORIZATION_CACHE);
                if (authentication_cache != null) {
                    authorization_cache.remove(user.getUsername());
                }
                cleanActivityUserInfo(user.getId());
            }
        }
    }

    public List<Object> getOnlineUsers() {
        List<Object> onlineUsers = new ArrayList<>();
        Cache active_session_cache = getCache(Constant.ACTIVE_SESSION_CACHE);
        if (active_session_cache == null) {
            return onlineUsers;
        }

        if (active_session_cache.values() != null) {
            for (Object o : active_session_cache.values()) {
                SimpleSession session = JSONUtil.toBean(JSONUtil.parseObj(o), SimpleSession.class);
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("username", getUser(session).getUsername());
                userMap.put("sessionId", session.getId());
                userMap.put("host", session.getHost());
                userMap.put("isValid", session.isValid());
                userMap.put("startTimestamp", session.getStartTimestamp());
                userMap.put("lastAccessTime", session.getLastAccessTime());
                userMap.put("timeout", TimeUnit.MILLISECONDS.toMinutes(session.getTimeout()));
                onlineUsers.add(userMap);
            }
        }
        return onlineUsers;
    }

    public User getUser(SimpleSession simpleSession) {
        Object attribute = simpleSession.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
        List<User> userList = JSONUtil.toList(JSONUtil.parseArray(attribute), User.class);
        return userList.get(0);
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

    public Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

}
