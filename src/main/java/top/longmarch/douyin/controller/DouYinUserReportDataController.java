package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinUserReportDataService;

import java.util.ArrayList;
import java.util.List;

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
        List<ExternalUserComment> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserCommentGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/dataExternalUserItemGet")
    public Result dataExternalUserItemGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalUserItem> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserItemGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/dataExternalUserFansGet")
    public Result dataExternalUserFansGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalUserFans> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserFansGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/dataExternalUserLikeGet")
    public Result dataExternalUserLikeGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalUserLike> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserLikeGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/dataExternalUserProfileGet")
    public Result dataExternalUserProfileGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalUserProfile> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserProfileGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

    @GetMapping("/dataExternalUserShareGet")
    public Result dataExternalUserShareGet(@RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalUserShare> response = new ArrayList<>();
        try {
            response = douYinUserReportDataService.dataExternalUserShareGet(dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(response);
    }

}
