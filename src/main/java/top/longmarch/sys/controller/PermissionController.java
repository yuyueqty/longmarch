package top.longmarch.sys.controller;


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
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.tree.TreeUtil;
import top.longmarch.sys.entity.Permission;
import top.longmarch.sys.entity.Role;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.sys.entity.vo.PermissionTree;
import top.longmarch.sys.service.IPermissionService;

import java.util.*;
import java.util.stream.Collectors;

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
        List<Permission> permissionAllList = permissionService.list();
        List<Permission> menuList = permissionAllList.stream().filter(p -> p.getType() == 1).collect(Collectors.toList());

        List<PermissionTree> permissionAllTreeList = permissionAllList.stream().map(permission -> {
            PermissionTree permissionTree = new PermissionTree();
            BeanUtils.copyProperties(permission, permissionTree);
            permissionTree.setPid(permission.getParentId());
            return permissionTree;
        }).collect(Collectors.toList());
        List<PermissionTree> permissionTrees = TreeUtil.list2Tree(permissionAllTreeList);

        List<PermissionTree> menuAllTreeList = menuList.stream().map(permission -> {
            PermissionTree permissionTree = new PermissionTree();
            BeanUtils.copyProperties(permission, permissionTree);
            permissionTree.setPid(permission.getParentId());
            return permissionTree;
        }).collect(Collectors.toList());
        List<PermissionTree> permsList = TreeUtil.list2Tree(menuAllTreeList);
        return Result.ok().add(permissionTrees).add("permsList", permsList);
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
    @ApiOperation(value="修改权限状态")
    @RequiresPermissions("sys:permission:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改权限状态, 入参：{}", changeStatusDTO);
        Permission permission = new Permission();
        BeanUtils.copyProperties(changeStatusDTO, permission);
        permissionService.updateById(permission);
        return Result.ok().add(permission);
    }

    @Log
    @ApiOperation(value = "创建权限信息")
    @RequiresPermissions("sys:permission:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Permission permission) {
        log.info("创建权限信息, 入参：{}", permission);
        permissionService.save(permission);
        return Result.ok().add(permission);
    }

    @Log
    @ApiOperation(value = "更新权限信息")
    @RequiresPermissions("sys:permission:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Permission permission) {
        log.info("更新权限信息, 入参：{}", permission);
        permissionService.updateById(permission);
        return Result.ok().add(permission);
    }

    @Log
    @ApiOperation(value = "删除权限信息")
    @RequiresPermissions("sys:permission:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除权限信息, ids={}", ids);
        List<Permission> permissionList = permissionService.list(new LambdaQueryWrapper<Permission>().eq(Permission::getParentId, ids[0]));
        if (permissionList != null && permissionList.size() > 0) {
            return Result.fail("请先删除子分类节点");
        }
        permissionService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @ApiOperation(value = "获取权限父节点集")
    @GetMapping("/getPIds/{id}")
    public Result getPIds(@PathVariable Long id) {
        List<Long> pIds = new ArrayList<>();
        Permission permission = permissionService.getById(id);
        if (permission == null) {
            Result.ok().add(pIds);
        }
        if (permission.getParentId() == 0) {
            pIds.add(id);
            Result.ok().add(pIds);
        }
        getPidList(pIds, permission);
        Collections.sort(pIds);
        return Result.ok().add(pIds);
    }

    private void getPidList(List<Long> pIds, Permission permission) {
        if (permission.getParentId() == 0) {
            return;
        }
        pIds.add(permission.getParentId());
        getPidList(pIds, permissionService.getById(permission.getParentId()));
    }

}
