package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.FansListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.douyin.service.DouYinUserService;

import java.util.HashMap;
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
    public Object getOauthUserinfo(@RequestParam String token) {
        try {
            return douYinUserService.getOauthUserinfo(token);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/getFansList")
    public Object getFansList(@RequestParam Integer count, @RequestParam Long cursor) {
        Map<String, Object> result = new HashMap<>();
        try {
            FansListResponse fansList = douYinUserService.getFansList(null, count, cursor);
            result.put("code", fansList.hashCode());
            result.put("data", fansList.getData().getList());
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @GetMapping("/getFollowingList")
    public Object getFollowingList(@RequestParam String token, @RequestParam Integer count, @RequestParam Long cursor) {
        try {
            return douYinUserService.getFollowingList(token, count, cursor);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
