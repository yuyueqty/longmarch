package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinHotsearchService;

/**
 * 热点视频数据
 */
@RestController
public class DouYinHotsearchController {

    @Autowired
    private DouYinHotsearchService douYinHotsearchService;


    @GetMapping("/hotsearchSentencesGet")
    public Object hotsearchSentencesGet() {
        try {
            return douYinHotsearchService.hotsearchSentencesGet();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/hotsearchTrendingSentencesGet")
    public Object hotsearchTrendingSentencesGet(@RequestParam Integer count, @RequestParam Long cursor) {
        try {
            return douYinHotsearchService.hotsearchTrendingSentencesGet(count, cursor);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/hotsearchVideosGet")
    public Object hotsearchVideosGet(@RequestParam String hotSentence) {
        try {
            return douYinHotsearchService.hotsearchVideosGet(hotSentence);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
