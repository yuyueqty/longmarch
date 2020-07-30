package top.longmarch.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.zhyd.oauth.model.AuthToken;
import top.longmarch.douyin.entity.DouyinAccount;

public class TokenUtil {

    public static void set(AuthToken token) {
        RedisUtil.put(key(), token);
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
        String authTokenJson = RedisUtil.get(key());
        if (authTokenJson == null || "".equals(authTokenJson)) {
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

    public static synchronized String clientToken() {
        String client_token = RedisUtil.get("douyin_client_token");
        if (StrUtil.isBlank(client_token)) {
            String url = "https://open.douyin.com/passport/open/client_token/?client_key=awi3wm9fql82mobq&client_secret=9105f13c041abc3877e38be08186c632&grant_type=client_credential";
            JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.get(url)).getJSONObject("data");
            Integer error_code = jsonObject.getInt("error_code");
            if (error_code == 0) {
                client_token = jsonObject.getStr("access_token");
                RedisUtil.put("douyin_client_token", client_token, 2);
            }
        }
        return client_token;
    }

    private static String key() {
        return String.format("%s_token_key", UserUtil.getUsername().toLowerCase());
    }

}
