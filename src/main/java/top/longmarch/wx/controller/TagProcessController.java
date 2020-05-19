package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.wx.service.IWxGzhApiService;

/**
 * <p>
 * 公众号粉丝分维解析标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Slf4j
@Api(value = "解析新标签", tags = "解析新标签")
@RestController
@RequestMapping("/wx/gzh-user")
public class TagProcessController {

    @Autowired
    private IWxGzhApiService wxGzhApiService;

    @GetMapping("/process")
    public Result process() {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                wxGzhApiService.tagAnalysis();
            }
        });
        return Result.ok();
    }

    @GetMapping("/wxuserTagRemove")
    public Result wxuserTagRemove() {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                wxGzhApiService.tagRemove();
            }
        });
        return Result.ok();
    }

}
