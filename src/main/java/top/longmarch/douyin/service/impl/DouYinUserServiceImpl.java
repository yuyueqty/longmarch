package top.longmarch.douyin.service.impl;

import com.douyin.open.api.FansListApi;
import com.douyin.open.api.FollowingListApi;
import com.douyin.open.api.UserInfoApi;
import com.douyin.open.models.UserFansFansInlineResponse200Data;
import com.douyin.open.models.UserFollowingFollowingInlineResponse200Data;
import com.douyin.open.models.UserUserInfoUserInfoInlineResponse200Data;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinUserService;

@Service
public class DouYinUserServiceImpl implements DouYinUserService {

    @Override
    public UserUserInfoUserInfoInlineResponse200Data getOauthUserinfo() {
        UserInfoApi apiInstance = new UserInfoApi();
        return apiInstance.oauthUserinfoGet(TokenUtil.openId(), TokenUtil.accessToken()).getData();
    }

    @Override
    public UserFansFansInlineResponse200Data getFansList(Integer count, Integer cursor) {
        FansListApi apiInstance = new FansListApi();
        return apiInstance.fansListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor).getData();
    }

    @Override
    public UserFollowingFollowingInlineResponse200Data getFollowingList(Integer count, Integer cursor) {
        FollowingListApi apiInstance = new FollowingListApi();
        return apiInstance.followingListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor).getData();
    }

}
