package top.longmarch.wx.service.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SyncLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String lock = "lock";

    private String key(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public synchronized boolean lock(String lock) {
        if (StrUtil.isNotBlank(key(lock))) {
            return false;
        }
        redisTemplate.opsForValue().set(lock, lock);
        return true;
    }

    public synchronized void unlock(String lock) {
        redisTemplate.delete(lock);
    }

}
