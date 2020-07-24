package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.FansListResponse;
import com.douyin.open.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.service.DouYinUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理信息
 * 用户公开信息，粉丝列表，关注列表
 */
@RestController
public class DouYinUserController {

    @Autowired
    private DouYinUserService douYinUserService;

    @GetMapping("/getOauthUserinfo")
    public Object getOauthUserinfo() {
        try {
            return douYinUserService.getOauthUserinfo();
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getFansList")
    public Object getFansList(@RequestParam Integer count, @RequestParam Long cursor) {
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
    public Object getFollowingList(@RequestParam Integer count, @RequestParam Long cursor) {
        try {
            return douYinUserService.getFollowingList(count, cursor);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
