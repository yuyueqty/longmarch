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
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.sys.entity.OperateLog;
import top.longmarch.sys.service.IOperateLogService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "操作日志模块", tags = "操作日志模块接口")
@RestController
@RequestMapping("/sys/operate-log")
public class OperateLogController {

    private static final Logger log = LoggerFactory.getLogger(OperateLogController.class);
    @Autowired
    private IOperateLogService perateLogService;

    @ApiOperation(value="搜索操作日志")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<OperateLog> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<OperateLog> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        wrapper.orderByDesc(OperateLog::getId);
        return Result.ok().add(perateLogService.page(page, wrapper));
    }

    @ApiOperation(value="查看操作日志")
    @RequiresPermissions("sys:operateLog:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        OperateLog operateLog = perateLogService.getById(id);
        return Result.ok().add(operateLog);
    }

    @Log
    @ApiOperation(value="创建操作日志")
    @RequiresPermissions("sys:operateLog:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody OperateLog operateLog) {
        log.info("创建操作日志, 入参：{}", operateLog);
        perateLogService.save(operateLog);
        return Result.ok().add(operateLog);
    }

    @Log
    @ApiOperation(value="更新操作日志")
    @RequiresPermissions("sys:operateLog:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody OperateLog operateLog) {
        log.info("更新操作日志, 入参：{}", operateLog);
        perateLogService.updateById(operateLog);
        return Result.ok().add(operateLog);
    }

    @Log
    @ApiOperation(value="删除操作日志")
    @RequiresPermissions("sys:operateLog:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除操作日志, ids={}", ids);
        perateLogService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
