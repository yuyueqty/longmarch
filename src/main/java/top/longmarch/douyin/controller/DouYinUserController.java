package top.longmarch.douyin.controller;

import com.douyin.open.models.UserFansFansInlineResponse200Data;
import com.douyin.open.models.UserUserInfoUserInfoInlineResponse200Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinUserService;
import top.longmarch.douyin.service.DouyinFansService;

/**
 * 用户管理信息
 * 用户公开信息，粉丝列表，关注列表
 */
@RestController
public class DouYinUserController {

    @Autowired
    private DouYinUserService douYinUserService;
    @Autowired
    private DouyinFansService douyinFansService;

    @GetMapping("/getOauthUserinfo")
    public Result getOauthUserinfo() {
        return Result.ok().add(douYinUserService.getOauthUserinfo());
    }

    @GetMapping("/getFansList")
    public Result getFansList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                              @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Integer cursor) {
        UserFansFansInlineResponse200Data data = douYinUserService.getFansList(count, cursor);
        douyinFansService.saveDouyinFans(data);
        return Result.ok().add(data);
    }

    @GetMapping("/getFollowingList")
    public Result getFollowingList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                                   @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Integer cursor) {
        return Result.ok().add(douYinUserService.getFollowingList(count, cursor));
    }

}
