package top.longmarch.douyin.service.impl;

import com.douyin.open.ApiException;
import com.douyin.open.client.DataExternalItemApi;
import com.douyin.open.model.*;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

@Service
public class DouYinVideoReportDataServiceImpl implements DouYinVideoReportDataService {

    @Override
    public DataExternalItemBaseResponse dataExternalItemBaseGet(String itemId) throws ApiException {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemBaseGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId);
    }

    @Override
    public DataExternalItemLikeResponse dataExternalItemLikeGet(String itemId, Integer dateType) throws ApiException {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemLikeGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemCommentResponse dataExternalItemCommentGet(String itemId, Integer dateType) throws ApiException {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemCommentGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemPlayResponse dataExternalItemPlayGet(String itemId, Integer dateType) throws ApiException {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemPlayGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType);
    }

    @Override
    public DataExternalItemShareResponse dataExternalItemShareGet(String itemId, Integer dateType) throws ApiException {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemShareGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType);
    }

}
