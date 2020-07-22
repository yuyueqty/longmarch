package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;

import java.io.File;

public interface DouYinVideoService {

    /**
     * 创建视频
     * @param token
     * @param body
     * @return
     */
    VideoCreateResponse videoCreate(String token, VideoCreateBody body) throws ApiException;

    /**
     * 删除视频
     * @param token
     * @param body
     * @return
     */
    VideoDeleteResponse videoDelete(String token, VideoDeleteBody body) throws ApiException;

    /**
     * 创建图片-直接发布到抖音
     * @param token
     * @param body
     * @return
     */
    ImageCreateResponse imageCreate(String token, ImageCreateBody body) throws ApiException;

    /**
     * 查询授权账号视频数据
     * @param token
     * @param count
     * @param cursor
     * @return
     */
    VideoListResponse videoList(String token, Integer count, Long cursor) throws ApiException;

    /**
     * 查询指定视频数据
     * 该接口用于查询用户特定视频的数据, 如点赞数, 播放数等，返回的数据是实时的。
     * @param token
     * @param body
     * @return
     */
    VideoDataResponse videoData(String token, VideoDataBody body) throws ApiException;

    /**
     * 上传视频到抖音服务器
     * @param token
     * @param video
     * @return
     */
    VideoUploadResponse videoUpload(String token, File video) throws ApiException;
}
