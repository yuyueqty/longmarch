package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.FansData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.service.DouYinUserFansPortraitService;

/**
 * 查询创作者粉丝数据
 * 该接口用于查询用户的粉丝数据，如性别分布，年龄分布，地域分布等。
 */
@RestController
public class DouYinUserFansPortraitController {

    @Autowired
    private DouYinUserFansPortraitService douYinUserFansPortraitService;

    @GetMapping("/fansDataGet")
    public Result fansDataGet() {
        FansData fansData = new FansData();
        try {
            fansData = douYinUserFansPortraitService.fansDataGet().getData().getFansData();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(fansData);
    }

}
