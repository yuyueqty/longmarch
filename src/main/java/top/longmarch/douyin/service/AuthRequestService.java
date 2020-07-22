package top.longmarch.douyin.service;

import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;

import javax.servlet.http.HttpServletResponse;

public interface AuthRequestService {

    String authorizeUrl(String source);

    String callbackUrl(HttpServletResponse response, AuthCallback authCallback, String source);

    AuthResponse refresh(String source, String token);

    AuthUser getAuthUser(String token);

    AuthToken getAuthToken(String token);

    void logout(String token);

}
