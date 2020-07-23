package top.longmarch.douyin.service;


import com.douyin.open.ApiException;
import com.douyin.open.model.HotsearchSentencesResponse;
import com.douyin.open.model.HotsearchTrendingSentencesResponse;
import com.douyin.open.model.HotsearchVideosResponse;

/**
 * https://open.douyin.com/platform/doc/OpenAPI-hotsearch
 */
public interface DouYinHotsearchService {

    /**
     * 获取实时热点词
     * @param token
     * @return
     */
    HotsearchSentencesResponse hotsearchSentencesGet(String token) throws ApiException;

    /**
     * 获取上升词
     * @param token
     * @param count 每页数量
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @return
     */
    HotsearchTrendingSentencesResponse hotsearchTrendingSentencesGet(String token, Integer count, Long cursor) throws ApiException;

    /**
     * 获取热点词聚合的视频
     * @param token
     * @param hotSentence 热点词
     * @return
     */
    HotsearchVideosResponse hotsearchVideosGet(String token, String hotSentence) throws ApiException;
}
