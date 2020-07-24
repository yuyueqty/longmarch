package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;

public interface DouYinUserReportDataService {

    /**
     * 获取用户视频情况
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     * @throws ApiException
     */
    DataExternalUserItemResponse dataExternalUserItemGet(Integer dateType) throws ApiException;

    /**
     * 获取用户粉丝数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserFansResponse dataExternalUserFansGet(Integer dateType) throws ApiException;

    /**
     * 获取用户点赞数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserLikeResponse dataExternalUserLikeGet(Integer dateType) throws ApiException;

    /**
     * 获取用户评论数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserCommentResponse dataExternalUserCommentGet(Integer dateType) throws ApiException;

    /**
     * 获取用户分享数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserShareResponse dataExternalUserShareGet(Integer dateType) throws ApiException;

    /**
     * 获取用户主页访问数
     *
     * @param dateType    近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    DataExternalUserProfileResponse dataExternalUserProfileGet(Integer dateType) throws ApiException;
}
