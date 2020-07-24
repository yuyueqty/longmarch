package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.DataExternalUserApi;
import com.douyin.open.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinUserReportDataService;

@Service
public class DouYinUserReportDataServiceImpl implements DouYinUserReportDataService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public DataExternalUserItemResponse dataExternalUserItemGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserItemGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

    @Override
    public DataExternalUserFansResponse dataExternalUserFansGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserFansGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

    @Override
    public DataExternalUserLikeResponse dataExternalUserLikeGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserLikeGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

    @Override
    public DataExternalUserCommentResponse dataExternalUserCommentGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserCommentGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

    @Override
    public DataExternalUserShareResponse dataExternalUserShareGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserShareGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

    @Override
    public DataExternalUserProfileResponse dataExternalUserProfileGet(Integer dateType) throws ApiException {
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserProfileGet(TokenUtil.openId(), TokenUtil.accessToken(), dateType);
    }

}
