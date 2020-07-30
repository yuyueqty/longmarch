package top.longmarch.douyin.service.impl;


import com.douyin.open.api.FansDataApi;
import com.douyin.open.models.FansDataFansDataInlineResponse200Data;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinUserFansPortraitService;

@Service
public class DouYinUserFansPortraitServiceImpl implements DouYinUserFansPortraitService {

    @Override
    public FansDataFansDataInlineResponse200Data fansDataGet() {
        FansDataApi apiInstance = new FansDataApi();
        return apiInstance.fansDataGet(TokenUtil.openId(), TokenUtil.accessToken()).getData();
    }

}
