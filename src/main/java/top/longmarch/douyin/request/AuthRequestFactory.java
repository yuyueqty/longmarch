package top.longmarch.douyin.request;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthToutiaoRequest;

public class AuthRequestFactory {

    private static final String callBackUrl = "https://tiaoyue.longmarch.top/lmapi/%s/callback";

    private AuthRequestFactory() {
    }

    public static AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("aac4438d4d0093456022233d94eb7300891ea305c812129866484300c134b5d4")
                        .clientSecret("25f1d7ea5074f0265116dfb18698965b9da2d28dbfa3583b6a4c6870ce7d9eee")
                        .redirectUri(String.format(callBackUrl, "gitee"))
                        .build());
                break;
            case "douyin":
                authRequest = new TiaoYueAuthDouyinRequest(AuthConfig.builder()
                        .clientId("awi3wm9fql82mobq")
//                        .clientId("awvjb8angr6mbxs8")
                        .clientSecret("9105f13c041abc3877e38be08186c632")
//                        .clientSecret("263766870ad90c0066fe6f20c5956ac7")
                        .redirectUri(String.format(callBackUrl, "douyin"))
                        .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                        .clientId("awi3wm9fql82mobq")
                        .clientSecret("9105f13c041abc3877e38be08186c632")
                        .redirectUri(String.format(callBackUrl, "toutiao"))
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }

}
