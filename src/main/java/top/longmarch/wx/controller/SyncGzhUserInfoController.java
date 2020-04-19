package top.longmarch.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserService;

import java.util.List;
import java.util.stream.Collectors;

@Api(value = "同步公众号用户信息", tags = "同步Api接口")
@RestController
@RequestMapping("/wx/gzh-user")
public class SyncGzhUserInfoController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;

    @ApiOperation(value = "同步微信用户信息")
    @GetMapping("/syncWxUserInfo")
    public Result syncWxUserInfo(@RequestParam(required = false, defaultValue = "false") Boolean batchSync) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }

        Integer syncUserNum = 0;
        try {
            syncUserNum = batchSyncWxUserInfo(gzhAccount, batchSync);
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }
        return Result.ok().add(syncUserNum);
    }

    private Integer batchSyncWxUserInfo(GzhAccount gzhAccount, Boolean batchSync) throws WxErrorException {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(gzhAccount.getWeixinAppid());
        config.setSecret(gzhAccount.getWeixinAppsecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);

        WxMpUserService userService = wxMpService.getUserService();
        String nextOpenid = userService.userList(null).getNextOpenid();
        if (batchSync) {
            nextOpenid = null;
        }
        List<String> openidList = userService.userList(nextOpenid).getOpenids();
        if (!CollectionUtils.isEmpty(openidList)) {
            batchDeleteOldWxUserInfo(gzhAccount, openidList);
            List<WxMpUser> userInfoList = userService.userInfoList(openidList);
            batchSaveWxUserInfo(gzhAccount, userInfoList);
        }
        return openidList.size();
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
