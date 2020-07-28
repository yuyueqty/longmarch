package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.HotsearchSentencesResponse;
import com.douyin.open.model.HotsearchTrendingSentencesResponse;
import com.douyin.open.model.HotsearchVideosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinHotsearchService;

/**
 * 热点视频数据
 */
@RestController
public class DouYinHotsearchController {

    @Autowired
    private DouYinHotsearchService douYinHotsearchService;


    @GetMapping("/hotsearchSentencesGet")
    public Result hotsearchSentencesGet() {
        HotsearchSentencesResponse response = new HotsearchSentencesResponse();
        try {
            response = douYinHotsearchService.hotsearchSentencesGet();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/hotsearchTrendingSentencesGet")
    public Result hotsearchTrendingSentencesGet(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                                                @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor) {
        HotsearchTrendingSentencesResponse response = new HotsearchTrendingSentencesResponse();
        try {
            response = douYinHotsearchService.hotsearchTrendingSentencesGet(count, cursor);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/hotsearchVideosGet")
    public Result hotsearchVideosGet(@RequestParam String hotSentence) {
        HotsearchVideosResponse response = new HotsearchVideosResponse();
        try {
            response = douYinHotsearchService.hotsearchVideosGet(hotSentence);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

}
