package top.longmarch.douyin.service.impl;

import cn.hutool.json.JSONUtil;
import com.douyin.open.api.DiscoveryApi;
import com.douyin.open.models.DiscoveryEntRankRankInlineResponse2001Data;
import com.douyin.open.models.DiscoveryEntRankRankInlineResponse200Data;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.RedisUtil;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinDiscoveryEntRankItemService;

@Service
public class DouYinDiscoveryEntRankItemServiceImpl implements DouYinDiscoveryEntRankItemService {

    private static final String key1 = "discovery_ent_rank_item";
    private static final String key2 = "discovery_ent_rank_version";

    @Override
    public DiscoveryEntRankRankInlineResponse200Data discoveryEntRankItemGet(Integer type, Integer version) {
        DiscoveryEntRankRankInlineResponse200Data data;
        String key_new = key1 + "_" + type;
        String value = RedisUtil.get(key_new);
        if (value == null) {
            DiscoveryApi apiInstance = new DiscoveryApi();
            data = apiInstance.discoveryEntRankItemGet(TokenUtil.clientToken(), type, version).getData();
            RedisUtil.put(key_new, data);
        } else {
            data = JSONUtil.toBean(value, DiscoveryEntRankRankInlineResponse200Data.class);
        }
        return data;
    }

    @Override
    public DiscoveryEntRankRankInlineResponse2001Data discoveryEntRankVersionGet(Integer type, Integer count, Integer cursor) {
        DiscoveryEntRankRankInlineResponse2001Data data;
        String key_new = key2 + "_" + type + "_" + cursor;
        String value = RedisUtil.get(key_new);
        if (value == null) {
            DiscoveryApi apiInstance = new DiscoveryApi();
            data = apiInstance.discoveryEntRankVersionGet(TokenUtil.clientToken(), count, type, cursor).getData();
            RedisUtil.put(key_new, data);
        } else {
            data = JSONUtil.toBean(value, DiscoveryEntRankRankInlineResponse2001Data.class);
        }
        return data;
    }

}
