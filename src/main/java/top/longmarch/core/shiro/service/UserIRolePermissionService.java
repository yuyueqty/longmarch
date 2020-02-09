package top.longmarch.core.shiro.service;//package top.longmarch.core.shiro.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;
import top.longmarch.core.enums.StatusEnum;
import top.longmarch.sys.entity.*;
import top.longmarch.sys.service.*;

import java.util.*;
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
    private IRolePermissionRelService rolePermissionRelService;
    @Autowired
    private IDepartmentService departmentService;


    public User getUserByUserName(String username) {
        return userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    public User getUserByUserId(Long userId) {
        return userService.getById(userId);
    }

    public Set<String> getUserPermissionByUserId(Long userId) {
        return getMenuAndPermissionIds(userId, Constant.BUTTON);
    }

    public Set<String> getUserMenusByUserId(Long userId) {
        return getMenuAndPermissionIds(userId, Constant.MENU);
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

    public Map<String, Object> getRoleDeptIdsByUserId(Long userId) {
        Map<String, Object> dataPerm = new HashMap<>();
        // 用户关联角色列表
        List<UserRoleRel> userRoleRelList = userRoleRelService.list(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, userId));
        if (null == userRoleRelList || userRoleRelList.size() == 0) {
            return dataPerm;
        }
        // 角色ID集合
        Set<Long> roleSet = userRoleRelList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toSet());
        // 根据角色ID集合查询角色对象（DataPerm 字段排序，将权限类型最大的排在最上面）
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>()
                .eq(Role::getStatus, StatusEnum.YES.getValue()).in(Role::getId, roleSet).orderByDesc(Role::getDataPerm));

        if (roleList != null && roleList.size() > 0) {
            // 获取最大的权限类型
            Integer type = roleList.get(0).getDataPerm();
            if (type == Constant.MORE_USER) {
                Set<Long> userIdList = new LinkedHashSet<>();
                // 多个角色配置了相同的权限类型
                List<String> dataPermIdStrList = roleList.stream().filter(role -> role.getDataPerm() == 2)
                        .map(role -> role.getDataPermIds()).collect(Collectors.toList());
                for (String s : dataPermIdStrList) {
                    if (StrUtil.isBlank(s)) {
                        continue;
                    }
                    String[] split = s.split(",");
                    if (split != null && split.length > 0) {
                        // 角色选取的部门ID
                        String deptId = split[split.length -1];
                        // 部门下所有子部门集合
                        Set<Long> deptIdList = departmentService.getDownDeptIds(Long.valueOf(deptId));
                        // 根据部门ID获取用户ID
                        List<User> userList = userService.list(new LambdaQueryWrapper<User>()
                                .eq(User::getStatus, StatusEnum.YES.getValue())
                                .in(User::getDeptId, deptIdList));
                        // 构造用户ID集合
                        Set<Long> userIds = userList.stream().map(User::getId).collect(Collectors.toSet());
                        userIdList.addAll(userIds);
                    }
                }
                dataPerm.put("type", Constant.MORE_USER);
                dataPerm.put("userIdSet", userIdList);
            } else {
                dataPerm.put("type", type);
            }
        }
        return dataPerm;
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
