package top.longmarch.douyin.service;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;

import javax.servlet.http.HttpServletResponse;

public interface AuthRequestService {

    String authorizeUrl(String source);

    String callbackUrl(HttpServletResponse response, AuthCallback authCallback, String source);

    void refreshToken(String source, String openId);

    void logout(String token);

}
