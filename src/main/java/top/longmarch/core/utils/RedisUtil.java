package top.longmarch.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.longmarch.core.config.ApplicationContextManager;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static StringRedisTemplate stringRedisTemplate = ApplicationContextManager.getBean(StringRedisTemplate.class);
    public static final int DEFAULT_TIMEOUT = 2;

    public static void put(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value));
    }

    public static void put(String key, Object value, Integer time) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, TimeUnit.HOURS);
    }

    public static String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

}
