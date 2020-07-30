package top.longmarch.douyin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

/**
 * 获取用户视频数据
 */
@RestController
public class DouYinVideoReportDataController {

    @Autowired
    private DouYinVideoReportDataService douYinVideoReportDataService;

    @GetMapping("/dataExternalItemBaseGet")
    public Result dataExternalItemBaseGet(@RequestParam String itemId) {
        return Result.ok().add(douYinVideoReportDataService.dataExternalItemBaseGet(itemId));
    }

    @GetMapping("/dataExternalItemCommentGet")
    public Result dataExternalItemCommentGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinVideoReportDataService.dataExternalItemCommentGet(itemId, dateType));
    }

    @GetMapping("/dataExternalItemLikeGet")
    public Result dataExternalItemLikeGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinVideoReportDataService.dataExternalItemLikeGet(itemId, dateType));
    }

    @GetMapping("/dataExternalItemPlayGet")
    public Result dataExternalItemPlayGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinVideoReportDataService.dataExternalItemPlayGet(itemId, dateType));
    }

    @GetMapping("/dataExternalItemShareGet")
    public Result dataExternalItemShareGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinVideoReportDataService.dataExternalItemShareGet(itemId, dateType));
    }

}
