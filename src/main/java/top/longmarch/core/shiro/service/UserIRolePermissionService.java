package top.longmarch.core.shiro.service;//package top.longmarch.core.shiro.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.enums.StatusEnum;
import top.longmarch.sys.entity.*;
import top.longmarch.sys.service.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserIRolePermissionService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IUserRoleRelService userRoleRelService;
    @Autowired
    IRolePermissionRelService rolePermissionRelService;

    public User getUserByUserName(String username) {
        return userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    public User getUserByUserId(Long userId) {
        return userService.getById(userId);
    }

    public Set<String> getUserPermissionByUserId(Long userId) {
        return getMenuAndPermissionIds(userId, 2);
    }

    public Set<String> getUserMenusByUserId(Long userId) {
        return getMenuAndPermissionIds(userId, 1);
    }

    public Set<String> getUserRoleByUserId(Long userId) {
        Set<String> roleNameSet = new LinkedHashSet<>();
        List<UserRoleRel> userRoleRelList = userRoleRelService.list(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, userId));
        if (null == userRoleRelList || userRoleRelList.size() == 0) {
            return roleNameSet;
        }
        Set<Long> roleSet = userRoleRelList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toSet());
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>().eq(Role::getStatus, StatusEnum.YES.getValue()).in(Role::getId, roleSet));
        return roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    private Set<String> getMenuAndPermissionIds(Long userId, Integer type) {
        Set<String> permissionStringSet = new LinkedHashSet<>();
        User user = getUserByUserId(userId);
        if (null == user || user.getStatus() == StatusEnum.NO.getValue()) {
            return permissionStringSet;
        }
        List<UserRoleRel> userRoleRelList = userRoleRelService.list(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, userId));
        if (null == userRoleRelList || userRoleRelList.size() == 0) {
            return permissionStringSet;
        }
        Set<Long> roleSet = userRoleRelList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toSet());
        List<RolePermissionRel> rolePermissionRelList = rolePermissionRelService.list(new LambdaQueryWrapper<RolePermissionRel>().in(RolePermissionRel::getRoleId, roleSet));
        if (null == rolePermissionRelList || rolePermissionRelList.size() == 0) {
            return permissionStringSet;
        }
        Set<Long> permissionIdSet = rolePermissionRelList.stream().map(RolePermissionRel::getPermissionId).collect(Collectors.toSet());
        List<Permission> permissionList = permissionService.list(new LambdaQueryWrapper<Permission>()
                .eq(Permission::getStatus, StatusEnum.YES.getValue())
                .eq(Permission::getType, type)
                .in(Permission::getId, permissionIdSet));
        permissionStringSet = permissionList.stream().map(Permission::getPermissionString).collect(Collectors.toSet());
        return permissionStringSet;
    }

}
