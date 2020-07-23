package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public Object dataExternalUserCommentGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserCommentGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserItemGet")
    public Object dataExternalUserItemGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserItemGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserFansGet")
    public Object dataExternalUserFansGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserFansGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserLikeGet")
    public Object dataExternalUserLikeGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserLikeGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserProfileGet")
    public Object dataExternalUserProfileGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserProfileGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalUserShareGet")
    public Object dataExternalUserShareGet(@RequestParam String token, @RequestParam Integer dateType) {
        try {
            return douYinUserReportDataService.dataExternalUserShareGet(token, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
