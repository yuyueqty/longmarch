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
import top.longmarch.sys.entity.UserRoleRel;
import top.longmarch.sys.service.IUserRoleRelService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色关联信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "用户角色关联信息模块", tags = "用户角色关联信息模块接口")
@RestController
@RequestMapping("/sys/user-role-rel")
public class UserRoleRelController {

    private static final Logger log = LoggerFactory.getLogger(UserRoleRelController.class);
    @Autowired
    private IUserRoleRelService serRoleRelService;

    @ApiOperation(value="搜索用户角色关联信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        IPage<UserRoleRel> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<UserRoleRel> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(serRoleRelService.page(page, wrapper));
    }

    @ApiOperation(value="查看用户角色关联信息")
    @RequiresPermissions("sys:userRoleRel:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        UserRoleRel userRoleRel = serRoleRelService.getById(id);
        return Result.ok().add(userRoleRel);
    }

    @Log
    @ApiOperation(value="创建用户角色关联信息")
    @RequiresPermissions("sys:userRoleRel:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody UserRoleRel userRoleRel) {
        log.info("创建用户角色关联信息, 入参：{}", userRoleRel);
        serRoleRelService.save(userRoleRel);
        return Result.ok().add(userRoleRel);
    }

    @Log
    @ApiOperation(value="更新用户角色关联信息")
    @RequiresPermissions("sys:userRoleRel:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody UserRoleRel userRoleRel) {
        log.info("更新用户角色关联信息, 入参：{}", userRoleRel);
        serRoleRelService.updateById(userRoleRel);
        return Result.ok().add(userRoleRel);
    }

    @Log
    @ApiOperation(value="删除用户角色关联信息")
    @RequiresPermissions("sys:userRoleRel:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除用户角色关联信息, ids={}", ids);
        serRoleRelService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
