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
import top.longmarch.core.common.Constant;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.FwtagRule;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.service.IFwtagRuleService;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import top.longmarch.wx.service.IGzhAccountService;

/**
 * <p>
 * 规则 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-05-09
*/
@Api(value = "规则模块", tags = "规则模块接口")
@RestController
@RequestMapping("/wx/fwtag-rule")
public class FwtagRuleController {

    private static final Logger log = LoggerFactory.getLogger(FwtagRuleController.class);
    @Autowired
    private IFwtagRuleService fwtagRuleService;
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "搜索规则")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<FwtagRule> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<FwtagRule> wrapper = new LambdaQueryWrapper<>();

        return Result.ok().add(fwtagRuleService.page(page, wrapper));
    }

    @ApiOperation(value = "规则")
    @RequiresPermissions("wx:fwtagRule:rule")
    @GetMapping("/rules")
    public Result tagRule() {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getDefaultAccount, 1)
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId()));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }
        List<FwtagRule> list = fwtagRuleService.list(new LambdaQueryWrapper<FwtagRule>()
                .eq(FwtagRule::getCreateBy, UserUtil.getUserId())
                .eq(FwtagRule::getGzhId, gzhAccount.getId()));

        Map<String, List<FwtagRule>> collect = list.stream().collect(Collectors.groupingBy(FwtagRule::getNewTag, Collectors.toList()));

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<FwtagRule>> entry : collect.entrySet()) {
            Map<String, Object> rule = new HashMap<>();
            rule.put("gzhId", gzhAccount.getId());
            rule.put("gzhName", gzhAccount.getJwid());
            rule.put("newTag", entry.getKey());
            List<Map<String, Object>> rules = entry.getValue().stream().map(fwtagRule -> {
                Map<String, Object> map = new HashMap<>();
                map.put("id", fwtagRule.getId());
                map.put("rid", fwtagRule.getRid());
                map.put("content", fwtagRule.getContent());
                map.put("score", fwtagRule.getScore());
                return map;
            }).collect(Collectors.toList());

            rule.put("rules", rules);
            result.add(rule);
        }
        return Result.ok().add(result);
    }

    @ApiOperation(value = "查看规则")
    @RequiresPermissions("wx:fwtagRule:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        FwtagRule fwtagRule = fwtagRuleService.getById(id);
        return Result.ok().add(fwtagRule);
    }

    @Log
    @ApiOperation(value = "创建规则")
    @PostMapping("/saveRule")
    public Result saveRule(@RequestBody List<FwtagRule> fwtagRule) {
        log.info("创建规则, 入参：{}", fwtagRule);
        fwtagRuleService.saveOrUpdateBatch(fwtagRule);
        return Result.ok().add(fwtagRule);
    }

    @Log
    @ApiOperation(value = "创建规则")
    @RequiresPermissions("wx:fwtagRule:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody FwtagRule fwtagRule) {
        log.info("创建规则, 入参：{}", fwtagRule);
        fwtagRuleService.save(fwtagRule);
        return Result.ok().add(fwtagRule);
    }

    @Log
    @ApiOperation(value = "更新规则")
    @RequiresPermissions("wx:fwtagRule:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody FwtagRule fwtagRule) {
        log.info("更新规则, 入参：{}", fwtagRule);
        fwtagRuleService.updateById(fwtagRule);
        return Result.ok().add(fwtagRule);
    }

    @Log
    @ApiOperation(value = "删除规则")
    @RequiresPermissions("wx:fwtagRule:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除规则, ids={}", ids);
        fwtagRuleService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改规则状态")
    @RequiresPermissions("wx:fwtagRule:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改规则状态, 入参：{}", changeStatusDTO);
        FwtagRule fwtagRule = new FwtagRule();
        BeanUtils.copyProperties(changeStatusDTO, fwtagRule);
        fwtagRuleService.updateById(fwtagRule);
        return Result.ok().add(fwtagRule);
    }

}
