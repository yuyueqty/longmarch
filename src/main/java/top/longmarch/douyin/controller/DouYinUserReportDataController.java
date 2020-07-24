package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.utils.TokenUtil;
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
    public Object dataExternalUserCommentGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserCommentGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserItemGet")
    public Object dataExternalUserItemGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserItemGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserFansGet")
    public Object dataExternalUserFansGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserFansGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserLikeGet")
    public Object dataExternalUserLikeGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserLikeGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserProfileGet")
    public Object dataExternalUserProfileGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserProfileGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserShareGet")
    public Object dataExternalUserShareGet(@RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserShareGet(dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
