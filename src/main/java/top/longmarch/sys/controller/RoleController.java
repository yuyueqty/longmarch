package top.longmarch.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import top.longmarch.core.common.Result;
import top.longmarch.sys.entity.Role;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.sys.service.IRoleService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Api(value = "角色信息模块", tags = "角色信息模块接口")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "搜索角色信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        return Result.ok().add(roleService.search(params));
    }

    @ApiOperation(value = "查看角色信息")
    @RequiresPermissions("sys:role:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return Result.ok().add(role);
    }

    @Log
    @ApiOperation(value = "修改角色状态")
    @RequiresPermissions("sys:role:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改角色状态, 入参：{}", changeStatusDTO);
        Role role = new Role();
        BeanUtils.copyProperties(changeStatusDTO, role);
        roleService.updateById(role);
        return Result.ok().add(role);
    }

    @Log
    @ApiOperation(value = "创建角色信息")
    @RequiresPermissions("sys:role:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Role role) {
        log.info("创建角色信息, 入参：{}", role);
        roleService.saveRole(role);
        return Result.ok().add(role);
    }

    @Log
    @ApiOperation(value = "更新角色信息")
    @RequiresPermissions("sys:role:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Role role) {
        log.info("更新角色信息, 入参：{}", role);
        roleService.updateRole(role);
        return Result.ok().add(role);
    }

    @Log
    @ApiOperation(value = "删除角色信息")
    @RequiresPermissions("sys:role:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除角色信息, ids={}", ids);
        roleService.removeRole(Arrays.asList(ids));
        return Result.ok();
    }

    @ApiOperation(value = "查询角色权限")
    @GetMapping("/show/perms/{id}")
    public Result showPerms(@PathVariable("id") Long id) {
        return Result.ok().add(roleService.selectRolePermissionById(id));
    }

    @ApiOperation(value = "加载所有角色")
    @GetMapping("/loadRoles")
    public Result loadRoles() {
        List<Map<String, Object>> roleList = roleService.listMaps(new QueryWrapper<Role>()
                .select("id as value, role_name as label"));
        return Result.ok().add(roleList);
    }

}
