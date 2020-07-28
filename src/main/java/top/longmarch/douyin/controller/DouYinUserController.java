package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.FansListResponse;
import com.douyin.open.model.FollowingListResponse;
import com.douyin.open.model.OauthUserinfoResponseData;
import com.douyin.open.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理信息
 * 用户公开信息，粉丝列表，关注列表
 */
@RestController
public class DouYinUserController {

    @Autowired
    private DouYinUserService douYinUserService;

    @GetMapping("/getOauthUserinfo")
    public Result getOauthUserinfo() {
        OauthUserinfoResponseData oauthUserinfo = new OauthUserinfoResponseData();
        try {
            oauthUserinfo = douYinUserService.getOauthUserinfo().getData();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(oauthUserinfo);
    }

    @GetMapping("/getFansList")
    public Result getFansList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                              @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor) {
        List<User> list = new ArrayList<>();
        try {
            FansListResponse fansList = douYinUserService.getFansList(count, cursor);
            list = fansList.getData().getList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(list);
    }

    @GetMapping("/getFollowingList")
    public Result getFollowingList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                                   @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor) {
        List<User> list = new ArrayList<>();
        try {
            FollowingListResponse followingList = douYinUserService.getFollowingList(count, cursor);
            list = followingList.getData().getList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(list);
    }

}
