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
    private static final String LOCK_VALUE = "lock";

    private String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public synchronized boolean lock(String key) {
        if (StrUtil.isNotBlank(getValue(key))) {
            return false;
        }
        redisTemplate.opsForValue().set(key, LOCK_VALUE);
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
        if (gzhAccount == null) {
            map.put("lock1", null);
            map.put("lock2", null);
            map.put("lock3", null);
            map.put("lock4", null);
        } else {
            map.put("lock1", getValue(getSynclock(gzhAccount)));
            map.put("lock2", getValue(getAnalyselock(gzhAccount)));
            map.put("lock3", getValue(getSecondlock(gzhAccount)));
            map.put("lock4", getValue(getRemovelock(gzhAccount)));
        }
        return map;
    }

}
