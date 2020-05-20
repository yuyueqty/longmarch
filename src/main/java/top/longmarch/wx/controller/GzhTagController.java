package top.longmarch.wx.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Collections;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhTag;
import top.longmarch.wx.entity.GzhTagRule;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhTagRuleService;
import top.longmarch.wx.service.IGzhTagService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 微信公众号标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-05-12
 */
@Api(value = "微信公众号标签模块", tags = "微信公众号标签模块接口")
@RestController
@RequestMapping("/wx/gzh-tag")
public class GzhTagController {

    private static final Logger log = LoggerFactory.getLogger(GzhTagController.class);
    @Autowired
    private IGzhTagService gzhTagService;
    @Autowired
    private IGzhTagRuleService gzhTagRuleService;
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "标签列表")
    @PostMapping("/list")
    public Result list() {
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        List<GzhTag> gzhTagList = gzhTagService.list(new LambdaQueryWrapper<GzhTag>().eq(GzhTag::getGzhId, gzhAccount.getId()));
        List<GzhTagRule> gzhTagRuleList = null;
        if (CollectionUtil.isNotEmpty(gzhTagList)) {
            Long id = gzhTagList.get(0).getId();
            gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>().eq(GzhTagRule::getTagId, id));
        }
        return Result.ok().add(gzhTagList).add("gzhTagRuleList", gzhTagRuleList);
    }

    @ApiOperation(value = "标签规则列表")
    @GetMapping("/loadRule/{tagId}")
    public Result loadRule(@PathVariable Long tagId) {
        List<GzhTagRule> list = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>().eq(GzhTagRule::getTagId, tagId));
        return Result.ok().add(list);
    }

    @ApiOperation(value = "搜索微信公众号标签")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhTag> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhTag> wrapper = new LambdaQueryWrapper<>();
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        wrapper.eq(GzhTag::getGzhId, gzhAccount.getId());

        return Result.ok().add(gzhTagService.page(page, wrapper));
    }

    @ApiOperation(value = "查看微信公众号标签")
    @RequiresPermissions("wx:gzhTag:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        GzhTag gzhTag = gzhTagService.getById(id);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "创建微信公众号标签")
    @RequiresPermissions("wx:gzhTag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhTag gzhTag) {
        log.info("创建微信公众号标签, 入参：{}", gzhTag);
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        try {
            WxUserTag wxUserTag = getWxMpService(gzhAccount).getUserTagService().tagCreate(gzhTag.getName());
            gzhTag.setWxTagId(wxUserTag.getId());
            gzhTag.setGzhId(gzhAccount.getId());
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }
        gzhTagService.save(gzhTag);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "更新微信公众号标签")
    @RequiresPermissions("wx:gzhTag:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhTag gzhTag) {
        log.info("更新微信公众号标签, 入参：{}", gzhTag);
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        try {
            getWxMpService(gzhAccount).getUserTagService().tagUpdate(gzhTag.getWxTagId(), gzhTag.getName());
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }
        gzhTagService.updateById(gzhTag);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "删除微信公众号标签")
    @RequiresPermissions("wx:gzhTag:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除微信公众号标签, ids={}", ids);
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        List<GzhTagRule> gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>()
                .eq(GzhTagRule::getGzhId, gzhAccount.getId())
                .in(GzhTagRule::getTagId, ids));
        if (CollectionUtil.isNotEmpty(gzhTagRuleList)) {
            return Result.ok("标签下存在规则，请先删除规则");
        }
        List<GzhTag> gzhTags = gzhTagService.listByIds(Arrays.asList(ids));
        try {
            WxMpUserTagService userTagService = getWxMpService(gzhAccount).getUserTagService();
            for (GzhTag gzhTag : gzhTags) {
                userTagService.tagDelete(gzhTag.getWxTagId());
            }
        } catch (WxErrorException e) {
            return Result.fail(e.getError().getErrorMsg());
        }

        gzhTagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改微信公众号标签状态")
    @RequiresPermissions("wx:gzhTag:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改微信公众号标签状态, 入参：{}", changeStatusDTO);
        GzhTag gzhTag = new GzhTag();
        BeanUtils.copyProperties(changeStatusDTO, gzhTag);
        gzhTagService.updateById(gzhTag);
        return Result.ok().add(gzhTag);
    }

    private GzhAccount getGzhAccount() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        return gzhAccount;
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
