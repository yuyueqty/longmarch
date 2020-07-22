package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.HotsearchApi;
import com.douyin.open.model.HotsearchSentencesResponse;
import com.douyin.open.model.HotsearchTrendingSentencesResponse;
import com.douyin.open.model.HotsearchVideosResponse;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinHotsearchService;

@Service
public class DouYinHotsearchServiceImpl implements DouYinHotsearchService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public HotsearchSentencesResponse hotsearchSentencesGet(String token) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchSentencesGet(authToken.getAccessToken());
    }

    @Override
    public HotsearchTrendingSentencesResponse hotsearchTrendingSentencesGet(String token, Integer count, Long cursor) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchTrendingSentencesGet(authToken.getAccessToken(), count, cursor);
    }

    @Override
    public HotsearchVideosResponse hotsearchVideosGet(String token, String hotSentence) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchVideosGet(authToken.getAccessToken(), hotSentence);
    }

}
