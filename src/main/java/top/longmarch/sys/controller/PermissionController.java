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
import top.longmarch.sys.entity.Permission;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.sys.entity.dto.CreateUpdatePermissionDTO;
import top.longmarch.sys.service.IPermissionService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 权限信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Api(value = "权限信息模块", tags = "权限信息模块接口")
@RestController
@RequestMapping("/sys/permission")
public class PermissionController {

    private static final Logger log = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "权限树")
    @PostMapping("/tree")
    public Result tree() {
        return Result.ok().add(permissionService.getPermissionTree());
    }

    @ApiOperation(value = "搜索权限信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        IPage<Permission> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(permissionService.page(page, wrapper));
    }

    @ApiOperation(value = "查看权限信息")
    @RequiresPermissions("sys:permission:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Permission permission = permissionService.getById(id);
        return Result.ok().add(permission);
    }

    @Log
    @ApiOperation(value = "修改权限状态")
    @RequiresPermissions("sys:permission:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改权限状态, 入参：{}", changeStatusDTO);
        permissionService.updateById(changeStatusDTO.convertPermission());
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "创建权限信息")
    @RequiresPermissions("sys:permission:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody CreateUpdatePermissionDTO createUpdatePermissionDTO) {
        log.info("创建权限信息, 入参：{}", createUpdatePermissionDTO);
        permissionService.saveOrUpdate(createUpdatePermissionDTO.convertPermission());
        return Result.ok().add(createUpdatePermissionDTO);
    }

    @Log
    @ApiOperation(value = "更新权限信息")
    @RequiresPermissions("sys:permission:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody CreateUpdatePermissionDTO createUpdatePermissionDTO) {
        log.info("更新权限信息, 入参：{}", createUpdatePermissionDTO);
        permissionService.saveOrUpdate(createUpdatePermissionDTO.convertPermission());
        return Result.ok().add(createUpdatePermissionDTO);
    }

    @Log
    @ApiOperation(value = "删除权限信息")
    @RequiresPermissions("sys:permission:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除权限信息, ids={}", ids);
        permissionService.removePermissionByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @ApiOperation(value = "获取权限父节点集")
    @GetMapping("/getPIds/{id}")
    public Result getPIds(@PathVariable Long id) {
        return Result.ok().add(permissionService.getParentIds(id));
    }

}
