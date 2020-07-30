package top.longmarch.douyin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinUserReportDataService;

/**
 * 用户相关数据接入
 * 视频情况，粉丝数，点赞数，评论数，分享数，主页访问数
 */
@RestController
public class DouYinUserReportDataController {

    @Autowired
    private DouYinUserReportDataService douYinUserReportDataService;

    @GetMapping("/dataExternalUserCommentGet")
    public Result dataExternalUserCommentGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserCommentGet(dateType));
    }

    @GetMapping("/dataExternalUserItemGet")
    public Result dataExternalUserItemGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserItemGet(dateType));
    }

    @GetMapping("/dataExternalUserFansGet")
    public Result dataExternalUserFansGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserFansGet(dateType));
    }

    @GetMapping("/dataExternalUserLikeGet")
    public Result dataExternalUserLikeGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserLikeGet(dateType));
    }

    @GetMapping("/dataExternalUserProfileGet")
    public Result dataExternalUserProfileGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserProfileGet(dateType));
    }

    @GetMapping("/dataExternalUserShareGet")
    public Result dataExternalUserShareGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        return Result.ok().add(douYinUserReportDataService.dataExternalUserShareGet(dateType));
    }

}
