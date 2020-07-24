package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.FansDataApi;
import com.douyin.open.model.FansDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinUserFansPortraitService;

@Service
public class DouYinUserFansPortraitServiceImpl implements DouYinUserFansPortraitService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public FansDataResponse fansDataGet() throws ApiException {
        FansDataApi apiInstance = new FansDataApi();
        return apiInstance.fansDataGet(TokenUtil.openId(), TokenUtil.accessToken());
    }

}
