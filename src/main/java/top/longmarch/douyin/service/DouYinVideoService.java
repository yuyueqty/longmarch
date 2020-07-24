package top.longmarch.douyin.service;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;

import java.io.File;

public interface DouYinVideoService {

    /**
     * 创建视频
     * @param body
     * @return
     */
    VideoCreateResponse videoCreate(VideoCreateBody body) throws ApiException;

    /**
     * 删除视频
     * @param body
     * @return
     */
    VideoDeleteResponse videoDelete(VideoDeleteBody body) throws ApiException;

    /**
     * 创建图片-直接发布到抖音
     * @param body
     * @return
     */
    ImageCreateResponse imageCreate(ImageCreateBody body) throws ApiException;

    /**
     * 查询授权账号视频数据
     * @param count
     * @param cursor
     * @return
     */
    VideoListResponse videoList(Integer count, Long cursor) throws ApiException;

    /**
     * 查询指定视频数据
     * 该接口用于查询用户特定视频的数据, 如点赞数, 播放数等，返回的数据是实时的。
     * @param body
     * @return
     */
    VideoDataResponse videoData(VideoDataBody body) throws ApiException;

    /**
     * 上传视频到抖音服务器
     * @param video
     * @return
     */
    VideoUploadResponse videoUpload(File video) throws ApiException;
}
