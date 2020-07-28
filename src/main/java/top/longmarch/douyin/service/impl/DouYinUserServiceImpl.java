package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.FansListApi;
import com.douyin.open.client.FollowingListApi;
import com.douyin.open.client.UserInfoApi;
import com.douyin.open.model.FansListResponse;
import com.douyin.open.model.FollowingListResponse;
import com.douyin.open.model.OauthUserinfoResponse;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinUserService;

@Service
public class DouYinUserServiceImpl implements DouYinUserService {

    @Override
    public OauthUserinfoResponse getOauthUserinfo() throws ApiException {
        UserInfoApi apiInstance = new UserInfoApi();
        return apiInstance.oauthUserinfoGet(TokenUtil.openId(), TokenUtil.accessToken());
    }

    @Override
    public FansListResponse getFansList(Integer count, Long cursor) throws ApiException {
        FansListApi apiInstance = new FansListApi();
        return apiInstance.fansListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor);
    }

    @Override
    public FollowingListResponse getFollowingList(Integer count, Long cursor) throws ApiException {
        FollowingListApi apiInstance = new FollowingListApi();
        return apiInstance.followingListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor);
    }

}
