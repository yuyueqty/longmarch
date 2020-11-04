package top.longmarch.douyin.request;

import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthDouyinRequest;
import me.zhyd.oauth.utils.UrlBuilder;

public class TiaoYueAuthDouyinRequest extends AuthDouyinRequest {

    public TiaoYueAuthDouyinRequest(AuthConfig config) {
        super(config);
    }

    public TiaoYueAuthDouyinRequest(AuthConfig config, AuthStateCache authStateCache) {
        super(config, authStateCache);
    }

    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(source.authorize())
                .queryParam("response_type", "code")
                .queryParam("client_key", config.getClientId())
                .queryParam("redirect_uri", config.getRedirectUri())
                .queryParam("scope", "user_info,video.create,video.delete,video.data,video.list,video.search,following.list,fans.list,hotsearch,fans.data,data.external.user,data.external.item,star_top_score_display,star_tops,star_author_score_display")
                .queryParam("state", getRealState(state))
                .build();
    }

}
