package top.longmarch.wx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhTagRule;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhTagRuleService;

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

    @Log
    @ApiOperation(value = "创建规则")
    @RequiresPermissions("wx:gzhTagRule:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhTagRule gzhTagRule) {
        log.info("创建规则, 入参：{}", gzhTagRule);
        GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
        gzhTagRule.setGzhId(gzhAccount.getId());
        gzhTagRuleService.save(gzhTagRule);
        return Result.ok().add(gzhTagRule);
    }

    @Log
    @ApiOperation(value = "更新规则")
    @RequiresPermissions("wx:gzhTagRule:update")
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
            GzhAccount gzhAccount = gzhAccountService.getDefalutGzhAccount();
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
    @GetMapping("/removeRule/{id}")
    public Result removeRule(@PathVariable Long id) {
        log.info("删除规则, id={}", id);
        gzhTagRuleService.removeById(id);
        return Result.ok();
    }

}
