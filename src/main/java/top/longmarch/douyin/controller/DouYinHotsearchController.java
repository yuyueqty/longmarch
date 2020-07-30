package top.longmarch.douyin.controller;

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
        return Result.ok().add(douYinHotsearchService.hotsearchSentencesGet());
    }

    @GetMapping("/hotsearchTrendingSentencesGet")
    public Result hotsearchTrendingSentencesGet(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                                                @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Integer cursor) {
        return Result.ok().add(douYinHotsearchService.hotsearchTrendingSentencesGet(count, cursor));
    }

    @GetMapping("/hotsearchVideosGet")
    public Result hotsearchVideosGet(@RequestParam String hotSentence) {
        return Result.ok().add(douYinHotsearchService.hotsearchVideosGet(hotSentence));
    }

}
