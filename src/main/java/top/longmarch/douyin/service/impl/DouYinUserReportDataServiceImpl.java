package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.DataExternalUserApi;
import com.douyin.open.model.*;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinUserReportDataService;

@Service
public class DouYinUserReportDataServiceImpl implements DouYinUserReportDataService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public DataExternalUserItemResponse dataExternalUserItemGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserItemGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

    @Override
    public DataExternalUserFansResponse dataExternalUserFansGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserFansGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

    @Override
    public DataExternalUserLikeResponse dataExternalUserLikeGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserLikeGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

    @Override
    public DataExternalUserCommentResponse dataExternalUserCommentGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserCommentGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

    @Override
    public DataExternalUserShareResponse dataExternalUserShareGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserShareGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

    @Override
    public DataExternalUserProfileResponse dataExternalUserProfileGet(String token, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalUserApi apiInstance = new DataExternalUserApi();
        return apiInstance.dataExternalUserProfileGet(authToken.getOpenId(), authToken.getAccessToken(), dateType);
    }

}
