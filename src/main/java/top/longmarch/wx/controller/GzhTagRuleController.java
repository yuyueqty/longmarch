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
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhTagRule;
import top.longmarch.wx.service.IGzhTagRuleService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "搜索规则")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhTagRule> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhTagRule> wrapper = new LambdaQueryWrapper<>();



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
    @ApiOperation(value = "删除规则")
    @RequiresPermissions("wx:gzhTagRule:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除规则, ids={}", ids);
        gzhTagRuleService.removeByIds(Arrays.asList(ids));
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

}
