package top.longmarch.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.sys.entity.LoginLog;
import top.longmarch.sys.service.ILoginLogService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Api(value = "模块", tags = "登录日志接口")
@RestController
@RequestMapping("/sys/login-log")
public class LoginLogController {

    private static final Logger log = LoggerFactory.getLogger(LoginLogController.class);
    @Autowired
    private ILoginLogService oginLogService;

    @ApiOperation(value = "搜索")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<LoginLog> page = PageFactory.getInstance(params);
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        LambdaQueryWrapper<LoginLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(LmUtils.isNotBlank(fuzzySearch), LoginLog::getUserName, fuzzySearch)
                .orderByDesc(LoginLog::getId);
        return Result.ok().add(oginLogService.page(page, wrapper));
    }

    @ApiOperation(value = "查看")
    @RequiresPermissions("sys:loginLog:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        LoginLog loginLog = oginLogService.getById(id);
        return Result.ok().add(loginLog);
    }

    @Log
    @ApiOperation(value = "创建")
    @RequiresPermissions("sys:loginLog:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody LoginLog loginLog) {
        log.info("创建, 入参：{}", loginLog);
        oginLogService.save(loginLog);
        return Result.ok().add(loginLog);
    }

    @Log
    @ApiOperation(value = "更新")
    @RequiresPermissions("sys:loginLog:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody LoginLog loginLog) {
        log.info("更新, 入参：{}", loginLog);
        oginLogService.updateById(loginLog);
        return Result.ok().add(loginLog);
    }

    @Log
    @ApiOperation(value = "删除")
    @RequiresPermissions("sys:loginLog:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除, ids={}", ids);
        oginLogService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
