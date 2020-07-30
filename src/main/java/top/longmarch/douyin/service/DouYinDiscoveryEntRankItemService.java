package top.longmarch.douyin.service;

import com.douyin.open.models.DiscoveryEntRankRankInlineResponse2001Data;
import com.douyin.open.models.DiscoveryEntRankRankInlineResponse200Data;

public interface DouYinDiscoveryEntRankItemService {

    DiscoveryEntRankRankInlineResponse200Data discoveryEntRankItemGet(Integer type, Integer version);

    DiscoveryEntRankRankInlineResponse2001Data discoveryEntRankVersionGet(Integer type, Integer count, Integer cursor);

}
