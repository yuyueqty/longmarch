package top.longmarch.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhTag;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhTagService;
import top.longmarch.wx.service.IGzhUserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 公众号粉丝分维解析标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Api(value = "公众号平台标签管理", tags = "公众号平台标签管理")
@RestController
@RequestMapping("/wx/gzh-tag")
public class SyncTagToWxController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private IGzhTagService gzhTagService;

    /**
     * 将标签批量同步到微信公众号平台
     * @param ids
     * @return
     */
    @PostMapping("/syncTagToWx")
    public Result syncTagToWx(@RequestBody Long[] ids) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        List<GzhTag> gzhTags = gzhTagService.listByIds(Arrays.asList(ids));
        try {
            WxMpService wxMpService = getWxMpService(gzhAccount);
            for (GzhTag gzhTag : gzhTags) {
                WxUserTag wxUserTag = wxMpService.getUserTagService().tagCreate(gzhTag.getName());
                gzhTag.setWxTagId(wxUserTag.getId());
            }
            gzhTagService.updateBatchById(gzhTags);
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }
        return Result.ok();
    }

    /**
     * 将指定标签批量打到当前公众号下符合条件的所有用户
     * @param tagId
     * @return
     */
    @GetMapping("/batchTagging/{tagId}")
    public Result batchTagging(@PathVariable Long tagId) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        WxMpService wxMpService = getWxMpService(gzhAccount);
        GzhTag gzhTag = gzhTagService.getById(tagId);
        if (gzhTag.getWxTagId() == null) {
            return Result.fail("请先将标签同步到微信公众号平台");
        }

        QueryWrapper<GzhUser> wrapper = new QueryWrapper<>();
        wrapper.select("id, open_id, tag_ids, fen_wei_tags")
                .eq("create_by", UserUtil.getUserId())
                .eq("gzh_id", gzhAccount.getId())
                .isNotNull("fen_wei_tags");
        List<GzhUser> gzhUserList = gzhUserService.list(wrapper);

        if (gzhUserList == null || gzhUserList.size() == 0) {
            return Result.ok().add(0);
        }

        List<String> openIdList = gzhUserList.stream().filter(u -> Arrays.asList(u.getFenWeiTags().split(","))
                .contains(gzhTag.getName())).map(u -> u.getOpenId()).collect(Collectors.toList());
        try {
            List<String> collect = openIdList.stream().filter(openId -> {
                try {
                    return wxMpService.getUserTagService().userTagList(openId).size() < 20;
                } catch (WxErrorException e) {
                    System.out.println(openId + ", " + e.getError().getErrorMsg());
                    return false;
                }
            }).collect(Collectors.toList());

            wxMpService.getUserTagService().batchTagging(gzhTag.getWxTagId(), collect.toArray(new String[openIdList.size()]));
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }

        return Result.ok().add(openIdList.size());
    }

    private WxMpService getWxMpService(GzhAccount gzhAccount) {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(gzhAccount.getWeixinAppid());
        config.setSecret(gzhAccount.getWeixinAppsecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

}
