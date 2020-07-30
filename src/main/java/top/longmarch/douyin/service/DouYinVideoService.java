package top.longmarch.douyin.service;

import com.douyin.open.models.*;

import java.io.File;

public interface DouYinVideoService {

    /**
     * 创建视频
     *
     * @param body
     * @return
     */
    VideoCreateAwemeCreateInlineResponse2001Data videoCreate(VideoCreateAwemeCreateBody1 body);

    /**
     * 删除视频
     *
     * @param body
     * @return
     */
    VideoDeleteAwemeDeleteInlineResponse200Data videoDelete(VideoDeleteAwemeDeleteBody body);

    /**
     * 创建图片-直接发布到抖音
     *
     * @param body
     * @return
     */
    ImageCreateImageCreateInlineResponse2001Data imageCreate(ImageCreateImageCreateBody1 body);

    /**
     * 查询授权账号视频数据
     *
     * @param count
     * @param cursor
     * @return
     */
    VideoListVideoListInlineResponse200Data videoList(Integer count, Integer cursor);

    /**
     * 查询指定视频数据
     * 该接口用于查询用户特定视频的数据, 如点赞数, 播放数等，返回的数据是实时的。
     *
     * @param body
     * @return
     */
    VideoDataVideoDataInlineResponse200Data videoData(VideoDataVideoDataBody body);

    /**
     * 上传视频到抖音服务器
     *
     * @param video
     * @return
     */
    VideoCreateAwemeCreateInlineResponse200Data videoUpload(File video);
}
