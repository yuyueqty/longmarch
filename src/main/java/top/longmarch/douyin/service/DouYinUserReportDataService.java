package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;

public interface DouYinUserReportDataService {

    /**
     * 获取用户视频情况
     *
     * @param token
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     * @throws ApiException
     */
    DataExternalUserItemResponse dataExternalUserItemGet(String token, Integer dateType) throws ApiException;

    /**
     * 获取用户粉丝数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserFansResponse dataExternalUserFansGet(String token, Integer dateType) throws ApiException;

    /**
     * 获取用户点赞数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserLikeResponse dataExternalUserLikeGet(String token, Integer dateType) throws ApiException;

    /**
     * 获取用户评论数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserCommentResponse dataExternalUserCommentGet(String token, Integer dateType) throws ApiException;

    /**
     * 获取用户分享数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserShareResponse dataExternalUserShareGet(String token, Integer dateType) throws ApiException;

    /**
     * 获取用户主页访问数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserProfileResponse dataExternalUserProfileGet(String token, Integer dateType) throws ApiException;
}
