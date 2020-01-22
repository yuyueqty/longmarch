package top.longmarch.sys.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
import top.longmarch.sys.entity.Parameter;
import top.longmarch.sys.service.IParameterService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 平台参数表 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Api(value = "平台参数表模块", tags = "平台参数表模块接口")
@RestController
@RequestMapping("/sys/parameter")
public class ParameterController {

    private static final Logger log = LoggerFactory.getLogger(ParameterController.class);
    @Autowired
    private IParameterService parameterService;

    @ApiOperation(value = "获取指定参数")
    @GetMapping("/getByParameterName/{parameterName}")
    public Result getParameterByName(@PathVariable String parameterName) {
        Parameter parameter = parameterService.getParameterByName(parameterName);
        JSONObject object = new JSONObject();
        object.put("paramId", parameter.getParamId());
        object.put("paramName", parameter.getParamName());
        object.put("paramValue", JSONUtil.parseObj(parameter.getParamValue()));
        object.put("remark", parameter.getRemark());
        return Result.ok().add(object);
    }

    @ApiOperation(value = "搜索平台参数表")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Parameter> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Parameter> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(parameterService.page(page, wrapper));
    }

    @ApiOperation(value = "查看平台参数表")
    @RequiresPermissions("sys:parameter:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Parameter parameter = parameterService.getById(id);
        return Result.ok().add(parameter);
    }

    @Log
    @ApiOperation(value = "创建平台参数表")
    @RequiresPermissions("sys:parameter:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Parameter parameter) {
        log.info("创建平台参数表, 入参：{}", parameter);
        parameterService.save(parameter);
        return Result.ok().add(parameter);
    }

    @Log
    @ApiOperation(value = "更新平台参数表")
    @RequiresPermissions("sys:parameter:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Parameter parameter) {
        log.info("更新平台参数表, 入参：{}", parameter);
        parameterService.updateParameter(parameter);
        return Result.ok().add(parameter);
    }

    @Log
    @ApiOperation(value = "删除平台参数表")
    @RequiresPermissions("sys:parameter:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除平台参数表, ids={}", ids);
        parameterService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
