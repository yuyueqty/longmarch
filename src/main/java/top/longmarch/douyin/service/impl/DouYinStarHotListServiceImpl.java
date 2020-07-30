package top.longmarch.douyin.service.impl;

import cn.hutool.json.JSONUtil;
import com.douyin.open.api.StarAuthorScoreApi;
import com.douyin.open.api.StarHotListApi;
import com.douyin.open.models.StarAuthorStarAuthorInlineResponse2001Data;
import com.douyin.open.models.StarAuthorStarAuthorInlineResponse200Data;
import com.douyin.open.models.StarDataStarDataInlineResponse200Data;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.RedisUtil;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinStarHotListService;

@Service
public class DouYinStarHotListServiceImpl implements DouYinStarHotListService {

    private static final String key1 = "star_hot_list_type";
    private static final String key2 = "star_author_score_v2_unique_id";

    @Override
    public StarDataStarDataInlineResponse200Data starHotListGet(Integer hotListType) {
        StarDataStarDataInlineResponse200Data data;
        String key_new = key1 + "_" + hotListType;
        String value = RedisUtil.get(key_new);
        if (value == null) {
            StarHotListApi apiInstance = new StarHotListApi();
            data = apiInstance.starHotListGet(TokenUtil.clientToken(), hotListType).getData();
            RedisUtil.put(key_new, data);
        } else {
            data = JSONUtil.toBean(value, StarDataStarDataInlineResponse200Data.class);
        }
        return data;
    }

    @Override
    public StarAuthorStarAuthorInlineResponse200Data starAuthorScoreGet() {
        StarAuthorScoreApi apiInstance = new StarAuthorScoreApi();
        return apiInstance.starAuthorScoreGet(TokenUtil.openId(), TokenUtil.accessToken()).getData();
    }

    @Override
    public StarAuthorStarAuthorInlineResponse2001Data starAuthorScoreV2Get(String uniqueId) {
        StarAuthorStarAuthorInlineResponse2001Data data;
        String key_new = key2 + "_" + uniqueId;
        String value = RedisUtil.get(key_new);
        if (value == null) {
            StarAuthorScoreApi apiInstance = new StarAuthorScoreApi();
            data = apiInstance.starAuthorScoreV2Get(TokenUtil.clientToken(), uniqueId).getData();
            RedisUtil.put(key_new, data);
        } else {
            data = JSONUtil.toBean(value, StarAuthorStarAuthorInlineResponse2001Data.class);
        }
        return data;
    }

}
