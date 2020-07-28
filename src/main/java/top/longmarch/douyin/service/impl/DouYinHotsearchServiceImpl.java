package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.HotsearchApi;
import com.douyin.open.model.HotsearchSentencesResponse;
import com.douyin.open.model.HotsearchTrendingSentencesResponse;
import com.douyin.open.model.HotsearchVideosResponse;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinHotsearchService;

@Service
public class DouYinHotsearchServiceImpl implements DouYinHotsearchService {

    @Override
    public HotsearchSentencesResponse hotsearchSentencesGet() throws ApiException {
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchSentencesGet(TokenUtil.accessToken());
    }

    @Override
    public HotsearchTrendingSentencesResponse hotsearchTrendingSentencesGet(Integer count, Long cursor) throws ApiException {
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchTrendingSentencesGet(TokenUtil.accessToken(), count, cursor);
    }

    @Override
    public HotsearchVideosResponse hotsearchVideosGet(String hotSentence) throws ApiException {
        HotsearchApi apiInstance = new HotsearchApi();
        return apiInstance.hotsearchVideosGet(TokenUtil.accessToken(), hotSentence);
    }

}
