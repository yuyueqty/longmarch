package top.longmarch.douyin.service;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.douyin.open.models.*;

public interface DouYinUserReportDataService {

    /**
     * 获取用户视频情况
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     * @throws ApiException
     */
    ExternalDataUserExternalDataUserInlineResponse200Data dataExternalUserItemGet(Integer dateType);

    /**
     * 获取用户粉丝数
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    ExternalDataUserExternalDataUserInlineResponse2001Data dataExternalUserFansGet(Integer dateType);

    /**
     * 获取用户点赞数
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    ExternalDataUserExternalDataUserInlineResponse2002Data dataExternalUserLikeGet(Integer dateType);

    /**
     * 获取用户评论数
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    ExternalDataUserExternalDataUserInlineResponse2003Data dataExternalUserCommentGet(Integer dateType);

    /**
     * 获取用户分享数
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    ExternalDataUserExternalDataUserInlineResponse2004Data dataExternalUserShareGet(Integer dateType);

    /**
     * 获取用户主页访问数
     *
     * @param dateType 近7/15天；输入7代表7天、15代表15天、30代表30天
     * @return
     */
    ExternalDataUserExternalDataUserInlineResponse2005Data dataExternalUserProfileGet(Integer dateType);
}
