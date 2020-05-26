package top.longmarch.wx.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhTag;
import top.longmarch.wx.entity.GzhTagRule;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhTagRuleService;
import top.longmarch.wx.service.IGzhTagService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Log
    @ApiOperation(value = "创建微信公众号标签")
    @RequiresPermissions("wx:gzhTag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhTag gzhTag) {
        log.info("创建微信公众号标签, 入参：{}", gzhTag);
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
        WxUserTag wxUserTag = wxGzhApiWraper.tagCreate(gzhTag.getName());
        if (wxUserTag != null) {
            gzhTag.setWxTagId(wxUserTag.getId());
        }
        gzhTag.setGzhId(gzhAccount.getId());
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
        WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
        wxGzhApiWraper.tagUpdate(gzhTag.getWxTagId(), gzhTag.getName());
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
        List<Long> tagIds = gzhTagService.listByIds(Arrays.asList(ids)).stream().map(gzhTag -> gzhTag.getWxTagId()).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(tagIds)) {
            WxGzhApiWraper wxGzhApiWraper = new WxGzhApiWraper(gzhAccount);
            wxGzhApiWraper.tagDeleteBatch(tagIds);
        }
        gzhTagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
