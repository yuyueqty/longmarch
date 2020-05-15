package top.longmarch.wx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhTagRule;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhTagRuleService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 规则 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-05-12
*/
@Api(value = "规则模块", tags = "规则模块接口")
@RestController
@RequestMapping("/wx/gzh-tag-rule")
public class GzhTagRuleController {

    private static final Logger log = LoggerFactory.getLogger(GzhTagRuleController.class);
    @Autowired
    private IGzhTagRuleService gzhTagRuleService;
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "搜索规则")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhTagRule> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhTagRule> wrapper = new LambdaQueryWrapper<>();
        GzhAccount gzhAccount = getGzhAccount();
        wrapper.eq(GzhTagRule::getGzhId, gzhAccount.getId());

        return Result.ok().add(gzhTagRuleService.page(page, wrapper));
    }

    @ApiOperation(value = "查看规则")
    @RequiresPermissions("wx:gzhTagRule:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        GzhTagRule gzhTagRule = gzhTagRuleService.getById(id);
        return Result.ok().add(gzhTagRule);
    }

    @Log
    @ApiOperation(value = "创建规则")
    @RequiresPermissions("wx:gzhTagRule:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhTagRule gzhTagRule) {
        log.info("创建规则, 入参：{}", gzhTagRule);
        GzhAccount gzhAccount = getGzhAccount();
        gzhTagRule.setGzhId(gzhAccount.getId());
        gzhTagRuleService.save(gzhTagRule);
        return Result.ok().add(gzhTagRule);
    }

    @Log
    @ApiOperation(value = "更新规则")
    @RequiresPermissions("wx:gzhTagRule:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhTagRule gzhTagRule) {
        log.info("更新规则, 入参：{}", gzhTagRule);
        gzhTagRuleService.updateById(gzhTagRule);
        return Result.ok().add(gzhTagRule);
    }

    @Log
    @ApiOperation(value = "更新规则")
//    @RequiresPermissions("wx:gzhTagRule:update")
    @PostMapping("/save/{tagId}")
    public Result save(@PathVariable Long tagId, @Validated @RequestBody GzhTagRule gzhTagRule) {
        log.info("更新规则, 入参：{}", gzhTagRule);
        if (gzhTagRule.getId() == null) {
            GzhTagRule one = gzhTagRuleService.getOne(new LambdaQueryWrapper<GzhTagRule>()
                    .eq(GzhTagRule::getTagId, tagId)
                    .eq(GzhTagRule::getRid, gzhTagRule.getRid()));
            if (one != null) {
                return Result.fail("同一标签下规则名称不能重复");
            }
            GzhAccount gzhAccount = getGzhAccount();
            gzhTagRule.setGzhId(gzhAccount.getId());
            gzhTagRule.setTagId(tagId);
            gzhTagRuleService.save(gzhTagRule);
        } else {
            gzhTagRuleService.updateById(gzhTagRule);
        }
        return Result.ok().add(gzhTagRule);
    }

    @Log
    @ApiOperation(value = "删除规则")
    @RequiresPermissions("wx:gzhTagRule:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除规则, ids={}", ids);
        gzhTagRuleService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "删除规则")
//    @RequiresPermissions("wx:gzhTagRule:delete")
    @GetMapping("/removeRule/{id}")
    public Result removeRule(@PathVariable Long id) {
        log.info("删除规则, id={}", id);
        gzhTagRuleService.removeById(id);
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改规则状态")
    @RequiresPermissions("wx:gzhTagRule:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改规则状态, 入参：{}", changeStatusDTO);
        GzhTagRule gzhTagRule = new GzhTagRule();
        BeanUtils.copyProperties(changeStatusDTO, gzhTagRule);
        gzhTagRuleService.updateById(gzhTagRule);
        return Result.ok().add(gzhTagRule);
    }

    private GzhAccount getGzhAccount() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId())
                .eq(GzhAccount::getDefaultAccount, 1));
        return gzhAccount;
    }

}
