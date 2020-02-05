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
import top.longmarch.sys.entity.RolePermissionRel;
import top.longmarch.sys.service.IRolePermissionRelService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色权限关联信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "角色权限关联信息模块", tags = "角色权限关联信息模块接口")
@RestController
@RequestMapping("/sys/role-permission-rel")
public class RolePermissionRelController {

    private static final Logger log = LoggerFactory.getLogger(RolePermissionRelController.class);
    @Autowired
    private IRolePermissionRelService olePermissionRelService;

    @ApiOperation(value="搜索角色权限关联信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        IPage<RolePermissionRel> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<RolePermissionRel> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(olePermissionRelService.page(page, wrapper));
    }

    @ApiOperation(value="查看角色权限关联信息")
    @RequiresPermissions("sys:rolePermissionRel:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        RolePermissionRel rolePermissionRel = olePermissionRelService.getById(id);
        return Result.ok().add(rolePermissionRel);
    }

    @Log
    @ApiOperation(value="创建角色权限关联信息")
    @RequiresPermissions("sys:rolePermissionRel:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody RolePermissionRel rolePermissionRel) {
        log.info("创建角色权限关联信息, 入参：{}", rolePermissionRel);
        olePermissionRelService.save(rolePermissionRel);
        return Result.ok().add(rolePermissionRel);
    }

    @Log
    @ApiOperation(value="更新角色权限关联信息")
    @RequiresPermissions("sys:rolePermissionRel:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody RolePermissionRel rolePermissionRel) {
        log.info("更新角色权限关联信息, 入参：{}", rolePermissionRel);
        olePermissionRelService.updateById(rolePermissionRel);
        return Result.ok().add(rolePermissionRel);
    }

    @Log
    @ApiOperation(value="删除角色权限关联信息")
    @RequiresPermissions("sys:rolePermissionRel:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除角色权限关联信息, ids={}", ids);
        olePermissionRelService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
