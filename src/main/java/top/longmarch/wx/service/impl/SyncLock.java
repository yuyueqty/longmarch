package top.longmarch.wx.service.impl;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.longmarch.wx.entity.GzhAccount;

import java.util.HashMap;
import java.util.Map;

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

    public synchronized String getSynclock(GzhAccount gzhAccount) {
        return "sync_lock_" + gzhAccount.getId() + "_" + gzhAccount.getFwField() + "_" + gzhAccount.getCreateBy();
    }

    public synchronized String getAnalyselock(GzhAccount gzhAccount) {
        return "analyse_lock_" + gzhAccount.getId() + "_" + gzhAccount.getFwField() + "_" + gzhAccount.getCreateBy();
    }

    public synchronized String getSecondlock(GzhAccount gzhAccount) {
        return "second_analyse_lock_" + gzhAccount.getId() + "_" + gzhAccount.getFwField() + "_" + gzhAccount.getCreateBy();
    }

    public String getRemovelock(GzhAccount gzhAccount) {
        return "remove_lock_" + gzhAccount.getId() + "_" + gzhAccount.getFwField() + "_" + gzhAccount.getCreateBy();
    }

    public synchronized Map<String, String> getAllLock(GzhAccount gzhAccount) {
        Map<String, String> map = new HashMap<>();
        map.put("lock1", getSynclock(gzhAccount));
        map.put("lock2", getAnalyselock(gzhAccount));
        map.put("lock3", getSecondlock(gzhAccount));
        map.put("lock4", getRemovelock(gzhAccount));
        return map;
    }

}
