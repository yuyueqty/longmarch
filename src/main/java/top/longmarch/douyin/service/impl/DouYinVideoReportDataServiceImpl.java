package top.longmarch.douyin.service.impl;

import com.douyin.open.api.DataExternalItemApi;
import com.douyin.open.models.*;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

@Service
public class DouYinVideoReportDataServiceImpl implements DouYinVideoReportDataService {

    @Override
    public ExternalDataItemExternalDataItemInlineResponse200Data dataExternalItemBaseGet(String itemId) {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemBaseGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId).getData();
    }

    @Override
    public ExternalDataItemExternalDataItemInlineResponse2001Data dataExternalItemLikeGet(String itemId, Integer dateType) {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemLikeGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType).getData();
    }

    @Override
    public ExternalDataItemExternalDataItemInlineResponse2002Data dataExternalItemCommentGet(String itemId, Integer dateType) {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemCommentGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType).getData();
    }

    @Override
    public ExternalDataItemExternalDataItemInlineResponse2003Data dataExternalItemPlayGet(String itemId, Integer dateType) {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemPlayGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType).getData();
    }

    @Override
    public ExternalDataItemExternalDataItemInlineResponse2004Data dataExternalItemShareGet(String itemId, Integer dateType) {
        DataExternalItemApi apiInstance = new DataExternalItemApi();
        return apiInstance.dataExternalItemShareGet(TokenUtil.openId(), TokenUtil.accessToken(), itemId, dateType).getData();
    }

}
