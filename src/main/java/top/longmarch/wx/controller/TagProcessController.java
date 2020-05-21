package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IWxGzhApiService;
import top.longmarch.wx.service.impl.SyncLock;

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
    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private SyncLock syncLock;

    @GetMapping("/tagAnalysis")
    public Result tagAnalysis() {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        String lock = syncLock.getSecondlock(gzhAccount);
        if (!syncLock.lock(lock)) {
            return Result.fail("正在二次解析中，请稍等...");
        }
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                wxGzhApiService.tagAnalysis(gzhAccount, lock);
            }
        });
        return Result.ok();
    }

    @GetMapping("/tagRemove")
    public Result tagRemove() {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        String lock = syncLock.getRemovelock(gzhAccount);
        if (!syncLock.lock(lock)) {
            return Result.fail("正在批量取消中，请稍等...");
        }
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                wxGzhApiService.tagRemove(gzhAccount, lock);
            }
        });
        return Result.ok();
    }

}
