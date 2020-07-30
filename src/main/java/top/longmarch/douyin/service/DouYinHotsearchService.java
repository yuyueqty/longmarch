package top.longmarch.douyin.service;


import com.douyin.open.models.HotsearchHotsearchInlineResponse2001Data;
import com.douyin.open.models.HotsearchHotsearchInlineResponse2002Data;
import com.douyin.open.models.HotsearchHotsearchInlineResponse200Data;

/**
 * https://open.douyin.com/platform/doc/OpenAPI-hotsearch
 */
public interface DouYinHotsearchService {

    /**
     * 获取实时热点词
     *
     * @return
     */
    HotsearchHotsearchInlineResponse200Data hotsearchSentencesGet();

    /**
     * 获取上升词
     *
     * @param count  每页数量
     * @param cursor 分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。
     * @return
     */
    HotsearchHotsearchInlineResponse2001Data hotsearchTrendingSentencesGet(Integer count, Integer cursor);

    /**
     * 获取热点词聚合的视频
     *
     * @param hotSentence 热点词
     * @return
     */
    HotsearchHotsearchInlineResponse2002Data hotsearchVideosGet(String hotSentence);
}
