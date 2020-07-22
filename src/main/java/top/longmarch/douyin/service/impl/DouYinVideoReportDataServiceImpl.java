package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.DataExternalItemApi;
import com.douyin.open.model.*;
import me.zhyd.oauth.model.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

@Service
public class DouYinVideoReportDataServiceImpl implements DouYinVideoReportDataService {

    @Autowired
    private AuthRequestService authRequestService;

    @Override
    public DataExternalItemBaseResponse dataExternalItemBaseGet(String token, String itemId) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemBaseGet(authToken.getOpenId(), authToken.getAccessToken(), itemId);
    }

    @Override
    public DataExternalItemLikeResponse dataExternalItemLikeGet(String token, String itemId, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemLikeGet(authToken.getOpenId(), authToken.getAccessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemCommentResponse dataExternalItemCommentGet(String token, String itemId, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemCommentGet(authToken.getOpenId(), authToken.getAccessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemPlayResponse dataExternalItemPlayGet(String token, String itemId, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemPlayGet(authToken.getOpenId(), authToken.getAccessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemShareResponse dataExternalItemShareGet(String token, String itemId, Integer dateType) throws ApiException {
        AuthToken authToken = authRequestService.getAuthToken(token);
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemShareGet(authToken.getOpenId(), authToken.getAccessToken(), itemId, dateType);
    }

}
