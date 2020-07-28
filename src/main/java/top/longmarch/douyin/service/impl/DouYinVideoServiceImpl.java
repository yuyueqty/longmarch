package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.*;
import com.douyin.open.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinVideoService;

import java.io.File;

@Service
public class DouYinVideoServiceImpl implements DouYinVideoService {

    @Override
    public VideoCreateResponse videoCreate(VideoCreateBody body) throws ApiException {
        VideoPublishApi apiInstance = new VideoPublishApi();
        return apiInstance.videoCreatePost(TokenUtil.openId(), TokenUtil.accessToken(), body);
    }

    @Override
    public VideoDeleteResponse videoDelete(VideoDeleteBody body) throws ApiException {
        VideoDeleteApi apiInstance = new VideoDeleteApi();
        return apiInstance.videoDeletePost(TokenUtil.openId(), TokenUtil.accessToken(), body);
    }

    @Override
    public ImageCreateResponse imageCreate(ImageCreateBody body) throws ApiException {
        ImageApi apiInstance = new ImageApi();
        return apiInstance.imageCreatePost(TokenUtil.openId(), TokenUtil.accessToken(), body);
    }

    @Override
    public VideoListResponse videoList(Integer count, Long cursor) throws ApiException {
        VideoListApi apiInstance = new VideoListApi();
        return apiInstance.videoListGet(TokenUtil.openId(), TokenUtil.accessToken(), count, cursor);
    }

    @Override
    public VideoDataResponse videoData(VideoDataBody body) throws ApiException {
        VideoDataApi apiInstance = new VideoDataApi();
        return apiInstance.videoDataPost(body, TokenUtil.openId(), TokenUtil.accessToken());
    }

    @Override
    public VideoUploadResponse videoUpload(File video) throws ApiException {
        VideoPublishApi apiInstance = new VideoPublishApi();
        VideoUploadResponse videoUploadResponse = apiInstance.videoUploadPost(video, TokenUtil.openId(), TokenUtil.accessToken());
        return videoUploadResponse;
    }

}
