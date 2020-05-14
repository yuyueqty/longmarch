package top.longmarch.wx.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserService;
import top.longmarch.wx.service.impl.SyncLock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private static final Integer pageSize = 100;
    private static final Integer syncNum = 10000;

    @ApiOperation(value = "选择同步微信用户信息")
    @RequiresPermissions("wx:gzhuser:sync")
    @PostMapping("/syncMore")
    public Result syncMoreWxUserInfo(@RequestBody Long[] ids) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        List<GzhUser> gzhUsers = gzhUserService.listByIds(Arrays.asList(ids));
        List<String> openIdList = gzhUsers.stream().map(u -> u.getOpenId()).collect(Collectors.toList());
        try {
            batchSyncWxUserInfoOne(gzhAccount, getWxMpService(gzhAccount).getUserService(), openIdList);
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }
        return Result.ok();
    }

    @ApiOperation(value = "同步微信用户信息")
    @RequiresPermissions("wx:gzhuser:sync")
    @GetMapping("/syncWxUserInfo")
    public Result syncWxUserInfo(@RequestParam(required = false, defaultValue = "false") Boolean batchSync) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        String lock = "sync_lock_" + gzhAccount.getId() + "_" + gzhAccount.getFwField() + "_" + gzhAccount.getCreateBy();
        if (!syncLock.lock(lock)) {
            return Result.fail("正在同步中，请稍等...");
        }

        WxMpService wxMpService = getWxMpService(gzhAccount);

        WxMpUserService userService = wxMpService.getUserService();
        WxMpUserList wxMpUserList = null;
        try {
            wxMpUserList = userService.userList(null);
        } catch (WxErrorException e) {
            syncLock.unlock(lock);
            return Result.fail(e.getError().getErrorMsg());
        }
        int count = run(gzhAccount, userService, wxMpUserList, lock);
        return Result.ok().add(count);
    }

    private WxMpService getWxMpService(GzhAccount gzhAccount) {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(gzhAccount.getWeixinAppid());
        config.setSecret(gzhAccount.getWeixinAppsecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    private int run(GzhAccount gzhAccount, WxMpUserService userService, WxMpUserList wxMpUserList, String lock) {
        int count = wxMpUserList.getCount();
        ThreadUtil.execute(() -> {
            if (count > syncNum) {
                moreHandle(gzhAccount, userService, wxMpUserList.getOpenids(), wxMpUserList.getNextOpenid(), lock);
            } else {
                oneHandle(gzhAccount, userService, wxMpUserList.getOpenids(), lock);
            }
            syncLock.unlock(lock);
        });
        return count;
    }

    private void moreHandle(GzhAccount gzhAccount, WxMpUserService userService, List<String> openidList, String nextOpenid, String lock) {
        if (StrUtil.isNotBlank(nextOpenid)) {
            WxMpUserList wxMpUserList;
            try {
                wxMpUserList = userService.userList(nextOpenid);
                oneHandle(gzhAccount, userService, openidList, lock);
                moreHandle(gzhAccount, userService, wxMpUserList.getOpenids(), nextOpenid, lock);
            } catch (WxErrorException e) {
                syncLock.unlock(lock);
                log.error("微信同步失败：", e.getError().getErrorMsg());
            }
        }
    }

    private void oneHandle(GzhAccount gzhAccount, WxMpUserService userService, List<String> openidList, String lock) {
        if (!CollectionUtils.isEmpty(openidList)) {
            try {
                if (openidList.size() > pageSize) {
                    batchSyncWxUserInfoMore(gzhAccount, userService, openidList);
                } else {
                    batchSyncWxUserInfoOne(gzhAccount, userService, openidList);
                }
            } catch (WxErrorException e) {
                syncLock.unlock(lock);
                log.error("微信同步失败：", e.getError().getErrorMsg());
            }
        }
    }

    private void batchSyncWxUserInfoMore(GzhAccount gzhAccount, WxMpUserService userService, List<String> openidList) throws WxErrorException {
        int pages = PageUtil.totalPage(openidList.size(), pageSize);
        for (int i = 0; i < pages; i++) {
            int start = i * pageSize;
            List<String> subOpenidList;
            if (i == (pages - 1)) {
                subOpenidList = openidList.subList(start, openidList.size());
            } else {
                subOpenidList = openidList.subList(start, pageSize + start);
            }
            batchSyncWxUserInfoOne(gzhAccount, userService, subOpenidList);
        }
    }

    private void batchSyncWxUserInfoOne(GzhAccount gzhAccount, WxMpUserService userService, List<String> openidList) throws WxErrorException {
        try {
            List<WxMpUser> userInfoList = userService.userInfoList(openidList);
            batchDeleteOldWxUserInfo(gzhAccount, openidList);
            batchSaveWxUserInfo(gzhAccount, userInfoList);
        } catch (WxErrorException e) {
            log.error("微信用户详情获取失败：", e.getError().getErrorMsg());
        }
    }

    private void batchDeleteOldWxUserInfo(GzhAccount gzhAccount, List<String> openidList) {
        gzhUserService.remove(new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getGzhId, gzhAccount.getId())
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .in(GzhUser::getOpenId, openidList));
    }

    private void batchSaveWxUserInfo(GzhAccount gzhAccount, List<WxMpUser> userInfoList) {
        List<GzhUser> gzhUserList = userInfoList.stream().map(wxMpUser -> {
            GzhUser gzhUser = new GzhUser();
            BeanUtils.copyProperties(wxMpUser, gzhUser);
            gzhUser.setGzhId(gzhAccount.getId());
            gzhUser.setJwid(gzhAccount.getJwid());
            return gzhUser;
        }).collect(Collectors.toList());
        gzhUserService.saveBatch(gzhUserList);
    }

}
