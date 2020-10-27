package top.longmarch.douyin.service;

import com.douyin.open.models.UserFansFansInlineResponse200Data;
import com.douyin.open.models.UserFollowingFollowingInlineResponse200Data;
import com.douyin.open.models.UserUserInfoUserInfoInlineResponse200Data;

public interface DouYinUserService {

    /**
     * 获取用户信息
     *
     * @return
     */
    UserUserInfoUserInfoInlineResponse200Data getOauthUserinfo();

    /**
     * 获取粉丝列表
     *
     * @return
     */
    UserFansFansInlineResponse200Data getFansList(Integer count, Long cursor);

    /**
     * 获取关注列表
     *
     * @return
     */
    UserFollowingFollowingInlineResponse200Data getFollowingList(Integer count, Long cursor);
}
