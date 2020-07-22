package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.*;
import com.douyin.open.model.*;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinVideoService;

import java.io.File;

@Service
public class DouYinVideoServiceImpl implements DouYinVideoService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public VideoCreateResponse videoCreate(String token, VideoCreateBody body) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        VideoPublishApi apiInstance = new VideoPublishApi();
        return apiInstance.videoCreatePost(authToken.getOpenId(), authToken.getAccessToken(), body);
    }

    @Override
    public VideoDeleteResponse videoDelete(String token, VideoDeleteBody body) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        VideoDeleteApi apiInstance = new VideoDeleteApi();
        return apiInstance.videoDeletePost(authToken.getOpenId(), authToken.getAccessToken(), body);
    }

    @Override
    public ImageCreateResponse imageCreate(String token, ImageCreateBody body) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        ImageApi apiInstance = new ImageApi();
        return apiInstance.imageCreatePost(authToken.getOpenId(), authToken.getAccessToken(), body);
    }

    @Override
    public VideoListResponse videoList(String token, Integer count, Long cursor) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        VideoListApi apiInstance = new VideoListApi();
        return apiInstance.videoListGet(authToken.getOpenId(), authToken.getAccessToken(), count, cursor);
    }

    @Override
    public VideoDataResponse videoData(String token, VideoDataBody body) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        VideoDataApi apiInstance = new VideoDataApi();
        return apiInstance.videoDataPost(body, authToken.getOpenId(), authToken.getAccessToken());
    }

    @Override
    public VideoUploadResponse videoUpload(String token, File video) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        VideoPublishApi apiInstance = new VideoPublishApi();
        VideoUploadResponse videoUploadResponse = apiInstance.videoUploadPost(video, authToken.getOpenId(), authToken.getAccessToken());
        return videoUploadResponse;
    }

}
