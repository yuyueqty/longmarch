package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;

public interface DouYinVideoReportDataService {

    /**
     * 获取视频基础数据
     * @param itemId
     * @return
     */
    DataExternalItemBaseResponse dataExternalItemBaseGet(String token, String itemId) throws ApiException;

    /**
     * 获取视频点赞数据
     * @param itemId
     * @param dateType
     * @return
     */
    DataExternalItemLikeResponse dataExternalItemLikeGet(String token, String itemId, Integer dateType) throws ApiException;

    /**
     * 获取视频评论数据
     * @param itemId
     * @param dateType
     * @return
     */
    DataExternalItemCommentResponse dataExternalItemCommentGet(String token, String itemId, Integer dateType) throws ApiException;

    /**
     * 获取视频播放数据
     * @param itemId
     * @param dateType
     * @return
     */
    DataExternalItemPlayResponse dataExternalItemPlayGet(String token, String itemId, Integer dateType) throws ApiException;

    /**
     * 获取视频分享数据
     * @param itemId
     * @param dateType
     * @return
     */
    DataExternalItemShareResponse dataExternalItemShareGet(String token, String itemId, Integer dateType) throws ApiException;

}
