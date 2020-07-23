package top.longmarch.douyin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.request.AuthRequestFactory;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouyinAccountService;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Service
public class AuthRequestServiceImpl implements AuthRequestService {

    //    @Value(value = "${tiaoyue.auth.webUri}")
    private String webUrl = "http://localhost:9528/auth-redirect";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DouyinAccountService douyinAccountService;

    @Override
    public String authorizeUrl(String source) {
        return AuthRequestFactory.getAuthRequest(source).authorize(AuthStateUtils.createState());
    }

    @Override
    public String callbackUrl(HttpServletResponse response, AuthCallback authCallback, String source) {
        AuthResponse authResponse = AuthRequestFactory.getAuthRequest(source).login(authCallback);
        if (authResponse != null && authResponse.ok()) {
            String token = AuthStateUtils.createState();
            AuthUser authUser = (AuthUser) authResponse.getData();
            saveToken(token, authUser);
//            CookieUtil.addCookie(response, "token", token, 0);
            System.out.println(JSONUtil.toJsonStr(authUser));
            return "http://127.0.0.1:8081/#/index/" + token;
        } else {
            System.out.println("授权失败");
            return "http://127.0.0.1:8081";
        }
    }

    @Override
    public AuthUser getAuthUser(String token) {
        System.out.println("===UUID====>" + token);
        String authUserJson = stringRedisTemplate.opsForValue().get(token);
        if (StrUtil.isBlank(authUserJson)) {
            return null;
        }
        return JSONUtil.toBean(authUserJson, AuthUser.class);
    }

    @Override
    public AuthToken getAuthToken(String token) {
        AuthToken authToken = getAuthUser(token).getToken();
        // 校验accessToken是否过期, expireIn单位是秒
        int expireIn = authToken.getExpireIn();

        return authToken;
    }

    @Override
    public void logout(String token) {
        stringRedisTemplate.delete(token);
    }

    @Override
    public AuthResponse refresh(String source, String token) {
        AuthResponse authResponse = AuthRequestFactory.getAuthRequest(source).refresh(getAuthToken(token));
        return authResponse;
    }

    private void saveToken(String token, AuthUser authUser) {
        stringRedisTemplate.opsForValue().set(token, JSONUtil.toJsonStr(authUser), 24, TimeUnit.HOURS);
        douyinAccountService.saveOrUpdateDouyinAccount(token);
    }

}
