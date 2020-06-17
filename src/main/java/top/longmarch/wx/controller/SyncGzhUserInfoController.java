package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserService;
import top.longmarch.wx.service.impl.SyncLock;

import java.util.Arrays;

@Slf4j
@Api(value = "同步公众号用户信息", tags = "同步Api接口")
@RestController
@RequestMapping("/wx/gzh-user")
public class SyncGzhUserInfoController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private SyncLock syncLock;


    @ApiOperation(value = "选择同步微信用户信息")
    @RequiresPermissions("wx:gzhuser:sync")
    @PostMapping("/syncMore")
    public Result syncMoreWxUserInfo(@RequestBody Long[] ids) {
        gzhUserService.syncMoreWxGzhUser(Arrays.asList(ids));
        return Result.ok();
    }

    @ApiOperation(value = "同步所有微信用户信息")
    @RequiresPermissions("wx:gzhuser:sync:all")
    @GetMapping("/syncWxUserInfo")
    public Result syncWxUserInfo() {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        String lock = syncLock.getSynclock(gzhAccount);
        if (!syncLock.lock(lock)) {
            return Result.fail("正在同步中，请稍等...");
        }
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                gzhUserService.syncBatchWxGzhUser(gzhAccount, lock);
            }
        });
        return Result.ok();
    }

}
