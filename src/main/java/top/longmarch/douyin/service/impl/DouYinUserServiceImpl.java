package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.FansListApi;
import com.douyin.open.client.FollowingListApi;
import com.douyin.open.client.UserInfoApi;
import com.douyin.open.model.FansListResponse;
import com.douyin.open.model.FollowingListResponse;
import com.douyin.open.model.OauthUserinfoResponse;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinUserService;

@Service
public class DouYinUserServiceImpl implements DouYinUserService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public OauthUserinfoResponse getOauthUserinfo(String token) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        UserInfoApi apiInstance = new UserInfoApi();
        return apiInstance.oauthUserinfoGet(authToken.getAccessToken(), authToken.getOpenId());
    }

    @Override
    public FansListResponse getFansList(String token, Integer count, Long cursor) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        FansListApi apiInstance = new FansListApi();
        return apiInstance.fansListGet(authToken.getOpenId(), authToken.getAccessToken(), count, cursor);
    }

    @Override
    public FollowingListResponse getFollowingList(String token, Integer count, Long cursor) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        FollowingListApi apiInstance = new FollowingListApi();
        return apiInstance.followingListGet(authToken.getOpenId(), authToken.getAccessToken(), count, cursor);
    }

}
