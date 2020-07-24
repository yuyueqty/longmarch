package top.longmarch.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.longmarch.core.config.ApplicationContextManager;
import top.longmarch.douyin.entity.DouyinAccount;

public class TokenUtil {

    private static StringRedisTemplate stringRedisTemplate = ApplicationContextManager.getBean(StringRedisTemplate.class);

    public static void set(AuthToken token) {
        stringRedisTemplate.opsForValue().set(key(), JSONUtil.toJsonStr(token));
    }

    public static void set(DouyinAccount account) {
        AuthToken authToken = new AuthToken();
        if (StrUtil.isNotBlank(account.getOpenId())) {
            authToken.setOpenId(account.getOpenId());
        }
        if (StrUtil.isNotBlank(account.getAccessToken())) {
            authToken.setAccessToken(account.getAccessToken());
        }
        if (StrUtil.isNotBlank(account.getRefreshToken())) {
            authToken.setRefreshToken(account.getRefreshToken());
        }
        if (account.getExpireIn() == null) {
            authToken.setExpireIn(0);
        } else {
            authToken.setExpireIn(account.getExpireIn());
        }
        set(authToken);
    }

    public static AuthToken get() {
        String authTokenJson = stringRedisTemplate.opsForValue().get(key());
        if (StrUtil.isBlank(authTokenJson)) {
            return new AuthToken();
        }
        return JSONUtil.toBean(authTokenJson, AuthToken.class);
    }

    public static String accessToken() {
        return get().getAccessToken();
    }

    public static String openId() {
        return get().getOpenId();
    }

    private static String refreshToken() {
        return get().getRefreshToken();
    }

    private static String key() {
        return String.format("%s_token_key", UserUtil.getUsername().toLowerCase());
    }

}
