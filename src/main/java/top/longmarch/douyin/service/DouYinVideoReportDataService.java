package top.longmarch.douyin.service;

import com.douyin.open.models.*;

public interface DouYinVideoReportDataService {

    /**
     * 获取视频基础数据
     *
     * @param itemId
     * @return
     */
    ExternalDataItemExternalDataItemInlineResponse200Data dataExternalItemBaseGet(String itemId);

    /**
     * 获取视频点赞数据
     *
     * @param itemId
     * @param dateType
     * @return
     */
    ExternalDataItemExternalDataItemInlineResponse2001Data dataExternalItemLikeGet(String itemId, Integer dateType);

    /**
     * 获取视频评论数据
     *
     * @param itemId
     * @param dateType
     * @return
     */
    ExternalDataItemExternalDataItemInlineResponse2002Data dataExternalItemCommentGet(String itemId, Integer dateType);

    /**
     * 获取视频播放数据
     *
     * @param itemId
     * @param dateType
     * @return
     */
    ExternalDataItemExternalDataItemInlineResponse2003Data dataExternalItemPlayGet(String itemId, Integer dateType);

    /**
     * 获取视频分享数据
     *
     * @param itemId
     * @param dateType
     * @return
     */
    ExternalDataItemExternalDataItemInlineResponse2004Data dataExternalItemShareGet(String itemId, Integer dateType);

}
