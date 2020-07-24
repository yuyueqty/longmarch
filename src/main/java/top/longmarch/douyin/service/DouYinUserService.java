package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.FansListResponse;
import com.douyin.open.model.FollowingListResponse;
import com.douyin.open.model.OauthUserinfoResponse;

public interface DouYinUserService {

    /**
     * 获取用户信息
     *
     * @return
     */
    OauthUserinfoResponse getOauthUserinfo() throws ApiException;

    /**
     * 获取粉丝列表
     *
     * @return
     */
    FansListResponse getFansList(Integer count, Long cursor) throws ApiException;

    /**
     * 获取关注列表
     *
     * @return
     */
    FollowingListResponse getFollowingList(Integer count, Long cursor) throws ApiException;
}
