package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.FansDataApi;
import com.douyin.open.model.FansDataResponse;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinUserFansPortraitService;

@Service
public class DouYinUserFansPortraitServiceImpl implements DouYinUserFansPortraitService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public FansDataResponse fansDataGet(String token) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        FansDataApi apiInstance = new FansDataApi();
        return apiInstance.fansDataGet(authToken.getOpenId(), authToken.getAccessToken());
    }

}
