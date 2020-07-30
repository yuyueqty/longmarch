package top.longmarch.douyin.service.impl;

import com.douyin.open.api.*;
import com.douyin.open.models.*;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinVideoService;

import java.io.File;

@Service
public class DouYinVideoServiceImpl implements DouYinVideoService {

    @Override
    public VideoCreateAwemeCreateInlineResponse2001Data videoCreate(VideoCreateAwemeCreateBody1 body) {
        VideoPublishApi apiInstance = new VideoPublishApi();
        return apiInstance.videoCreatePost(TokenUtil.openId(), TokenUtil.accessToken(), body).getData();
    }

    @Override
    public VideoDeleteAwemeDeleteInlineResponse200Data videoDelete(VideoDeleteAwemeDeleteBody body) {
        VideoDeleteApi apiInstance = new VideoDeleteApi();
        return apiInstance.videoDeletePost(TokenUtil.openId(), TokenUtil.accessToken(), body).getData();
    }

    @Override
    public ImageCreateImageCreateInlineResponse2001Data imageCreate(ImageCreateImageCreateBody1 body) {
        ImageApi apiInstance = new ImageApi();
        return apiInstance.imageCreatePost(TokenUtil.openId(), TokenUtil.accessToken(), body).getData();
    }

    @Override
    public VideoListVideoListInlineResponse200Data videoList(Integer count, Integer cursor) {
        VideoListApi apiInstance = new VideoListApi();
        return apiInstance.videoListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor).getData();
    }

    @Override
    public VideoDataVideoDataInlineResponse200Data videoData(VideoDataVideoDataBody body) {
        VideoDataApi apiInstance = new VideoDataApi();
        return apiInstance.videoDataPost(body, TokenUtil.openId(), TokenUtil.accessToken()).getData();
    }

    @Override
    public VideoCreateAwemeCreateInlineResponse200Data videoUpload(File video) {
        VideoPublishApi apiInstance = new VideoPublishApi();
        return apiInstance.videoUploadPost(video, TokenUtil.openId(), TokenUtil.accessToken()).getData();
    }

    public void videosRelease(File video) {
        String openId = TokenUtil.openId();
        String accessToken = TokenUtil.accessToken();
        VideoPublishApi apiInstance = new VideoPublishApi();
        VideoCreateAwemeCreateInlineResponse200Data data = apiInstance.videoUploadPost(video, openId, accessToken).getData();
        String videoId = data.getVideo().getVideoId();

        VideoCreateAwemeCreateBody1 createBody = new VideoCreateAwemeCreateBody1();
        createBody.setVideoId(videoId);
        createBody.setCoverTsp(2.3);
        createBody.setText("测试发布视频");
        VideoCreateAwemeCreateInlineResponse2001Data data1 = apiInstance.videoCreatePost(openId, accessToken, createBody).getData();
        String itemId = data1.getItemId();
        System.out.println(itemId);
    }

}
