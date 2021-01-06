package top.longmarch.douyin.service.impl;


import cn.hutool.json.JSONUtil;
import com.douyin.open.api.HotsearchApi;
import com.douyin.open.models.HotsearchHotsearchInlineResponse2001Data;
import com.douyin.open.models.HotsearchHotsearchInlineResponse2002Data;
import com.douyin.open.models.HotsearchHotsearchInlineResponse200Data;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.RedisUtil;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinHotsearchService;

@Service
public class DouYinHotsearchServiceImpl implements DouYinHotsearchService {

    private static final String key1 = "hot_search_sentences";
    private static final String key2 = "hot_search_trending_sentences";
    private static final String key3 = "hot_search_videos";

    @Override
    public HotsearchHotsearchInlineResponse200Data hotsearchSentencesGet() {
        HotsearchHotsearchInlineResponse200Data data;
        String value = RedisUtil.get(key1);
        if (value == null) {
            HotsearchApi apiInstance = new HotsearchApi();
            data = apiInstance.hotsearchSentencesGet(TokenUtil.clientToken()).getData();
            RedisUtil.put(key1, data, RedisUtil.DEFAULT_TIMEOUT);
        } else {
            data = JSONUtil.toBean(value, HotsearchHotsearchInlineResponse200Data.class);
        }
        return data;
    }

    @Override
    public HotsearchHotsearchInlineResponse2001Data hotsearchTrendingSentencesGet(Integer count, Long cursor) {
        HotsearchHotsearchInlineResponse2001Data data;
        String key_new = key2 + "_" + cursor;
        String value = RedisUtil.get(key_new + "_" + cursor);
        if (value == null) {
            HotsearchApi apiInstance = new HotsearchApi();
            data = apiInstance.hotsearchTrendingSentencesGet(TokenUtil.clientToken(), count, cursor).getData();
            RedisUtil.put(key_new, data, RedisUtil.DEFAULT_TIMEOUT);
        } else {
            data = JSONUtil.toBean(value, HotsearchHotsearchInlineResponse2001Data.class);
        }
        return data;
    }

    @Override
    public HotsearchHotsearchInlineResponse2002Data hotsearchVideosGet(String hotSentence) {
        HotsearchHotsearchInlineResponse2002Data data;
        String key_new = key3 + "_" + (hotSentence.hashCode() + 1000);
        String value = RedisUtil.get(key_new);
        if (value == null) {
            HotsearchApi apiInstance = new HotsearchApi();
            data = apiInstance.hotsearchVideosGet(TokenUtil.clientToken(), hotSentence).getData();
            RedisUtil.put(key_new, data, RedisUtil.DEFAULT_TIMEOUT);
        } else {
            data = JSONUtil.toBean(value, HotsearchHotsearchInlineResponse2002Data.class);
        }
        return data;
    }

}
