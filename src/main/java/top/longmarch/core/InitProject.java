package top.longmarch.core;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class InitProject {

    public static void main(String[] args) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() throws Exception {
        List<Entity> sys_user = Db.use().findAll("sys_user");
        System.out.println(sys_user);
        createUserRolePermission();
        createDictionary();
        createParameter();
        createScheduleJob();
    }

    private static void createUserRolePermission() throws Exception {
        // ==============================用户表===========================================
        Long uid_1 = Db.use().insertForGeneratedKey(
                Entity.create("sys_user")
                        .set("username", "admin")
                        .set("password", "d89267ba6e888426c8f798a04f2fb874")
                        .set("head_img_url", "http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r")
                        .set("phone", "18888888888")
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================角色表===========================================
        Long rid_1 = Db.use().insertForGeneratedKey(
                Entity.create("sys_role")
                        .set("role_name", "管理员角色")
                        .set("description", "管理员角色")
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================用户角色关联表===========================================
        Db.use().insert(
                Entity.create("sys_user_role_rel")
                        .set("user_id", uid_1)
                        .set("role_id", rid_1)
        );

        Long id_1 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", 0)
                        .set("parent_ids", "")
                        .set("permission_name", "系统管理")
                        .set("description", "系统管理")
                        .set("permission_string", "sys:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================用户管理===========================================
        Long id_2 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", String.format("%d", id_1))
                        .set("parent_ids", id_1)
                        .set("permission_name", "用户管理")
                        .set("description", "用户管理")
                        .set("permission_string", "sys:user:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_3 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_2)
                        .set("parent_ids", String.format("%d,%d", id_1, id_2))
                        .set("permission_name", "查看用户")
                        .set("description", "查看用户")
                        .set("permission_string", "sys:user:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_4 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_2)
                        .set("parent_ids", String.format("%d,%d", id_1, id_2))
                        .set("permission_name", "添加用户")
                        .set("description", "查看用户")
                        .set("permission_string", "sys:user:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_5 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_2)
                        .set("parent_ids", String.format("%d,%d", id_1, id_2))
                        .set("permission_name", "修改用户")
                        .set("description", "修改用户")
                        .set("permission_string", "sys:user:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_6 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_2)
                        .set("parent_ids", String.format("%d,%d", id_1, id_2))
                        .set("permission_name", "删除用户")
                        .set("description", "删除用户")
                        .set("permission_string", "sys:user:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_7 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_2)
                        .set("parent_ids", String.format("%d,%d", id_1, id_2))
                        .set("permission_name", "修改密码")
                        .set("description", "修改密码")
                        .set("permission_string", "sys:user:change:password")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================角色管理===========================================
        Long id_8 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "角色管理")
                        .set("description", "角色管理")
                        .set("permission_string", "sys:role:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_9 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_8)
                        .set("parent_ids", String.format("%d,%d", id_1, id_8))
                        .set("permission_name", "查看角色")
                        .set("description", "查看角色")
                        .set("permission_string", "sys:role:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_10 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_8)
                        .set("parent_ids", String.format("%d,%d", id_1, id_8))
                        .set("permission_name", "添加角色")
                        .set("description", "查看角色")
                        .set("permission_string", "sys:role:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_11 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_8)
                        .set("parent_ids", String.format("%d,%d", id_1, id_8))
                        .set("permission_name", "修改角色")
                        .set("description", "修改角色")
                        .set("permission_string", "sys:role:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_12 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_8)
                        .set("parent_ids", String.format("%d,%d", id_1, id_8))
                        .set("permission_name", "删除角色")
                        .set("description", "删除角色")
                        .set("permission_string", "sys:role:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================权限管理===========================================
        Long id_13 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "权限管理")
                        .set("description", "权限管理")
                        .set("permission_string", "sys:permission:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_14 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_13)
                        .set("parent_ids", String.format("%d,%d", id_1, id_13))
                        .set("permission_name", "查看权限")
                        .set("description", "查看权限")
                        .set("permission_string", "sys:permission:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_15 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_13)
                        .set("parent_ids", String.format("%d,%d", id_1, id_13))
                        .set("permission_name", "添加权限")
                        .set("description", "查看权限")
                        .set("permission_string", "sys:permission:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_16 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_13)
                        .set("parent_ids", String.format("%d,%d", id_1, id_13))
                        .set("permission_name", "修改权限")
                        .set("description", "修改权限")
                        .set("permission_string", "sys:permission:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_17 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_13)
                        .set("parent_ids", String.format("%d,%d", id_1, id_13))
                        .set("permission_name", "删除权限")
                        .set("description", "删除权限")
                        .set("permission_string", "sys:permission:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================部门管理===========================================
        Long id_18 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "部门管理")
                        .set("description", "部门管理")
                        .set("permission_string", "sys:department:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_19 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_18)
                        .set("parent_ids", String.format("%d,%d", id_1, id_18))
                        .set("permission_name", "查看部门")
                        .set("description", "查看部门")
                        .set("permission_string", "sys:department:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_20 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_18)
                        .set("parent_ids", String.format("%d,%d", id_1, id_18))
                        .set("permission_name", "添加部门")
                        .set("description", "查看部门")
                        .set("permission_string", "sys:department:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_21 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_18)
                        .set("parent_ids", String.format("%d,%d", id_1, id_18))
                        .set("permission_name", "修改部门")
                        .set("description", "修改部门")
                        .set("permission_string", "sys:department:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_22 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_18)
                        .set("parent_ids", String.format("%d,%d", id_1, id_18))
                        .set("permission_name", "删除部门")
                        .set("description", "删除部门")
                        .set("permission_string", "sys:department:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================字典管理===========================================
        Long id_23 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "字典管理")
                        .set("description", "字典管理")
                        .set("permission_string", "sys:dictionary:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_24 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_23)
                        .set("parent_ids", String.format("%d,%d", id_1, id_23))
                        .set("permission_name", "查看字典")
                        .set("description", "查看字典")
                        .set("permission_string", "sys:dictionary:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_25 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_23)
                        .set("parent_ids", String.format("%d,%d", id_1, id_23))
                        .set("permission_name", "添加字典")
                        .set("description", "查看字典")
                        .set("permission_string", "sys:dictionary:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_26 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_23)
                        .set("parent_ids", String.format("%d,%d", id_1, id_23))
                        .set("permission_name", "修改字典")
                        .set("description", "修改字典")
                        .set("permission_string", "sys:dictionary:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_27 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_23)
                        .set("parent_ids", String.format("%d,%d", id_1, id_23))
                        .set("permission_name", "删除字典")
                        .set("description", "删除字典")
                        .set("permission_string", "sys:dictionary:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================任务管理===========================================
        Long id_28 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "任务管理")
                        .set("description", "任务管理")
                        .set("permission_string", "sys:schedule:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_29 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "查看任务")
                        .set("description", "查看任务")
                        .set("permission_string", "job:schedule:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_30 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "添加任务")
                        .set("description", "查看任务")
                        .set("permission_string", "sys:schedule:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_31 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "修改任务")
                        .set("description", "修改任务")
                        .set("permission_string", "sys:schedule:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_32 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "删除任务")
                        .set("description", "删除任务")
                        .set("permission_string", "sys:schedule:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_33 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "执行任务")
                        .set("description", "执行任务")
                        .set("permission_string", "job:schedule:run")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_34 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "暂停任务")
                        .set("description", "暂停任务")
                        .set("permission_string", "sys:schedule:pause")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_35 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "恢复任务")
                        .set("description", "恢复任务")
                        .set("permission_string", "sys:schedule:resume")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_36 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "重置任务")
                        .set("description", "重置任务")
                        .set("permission_string", "sys:schedule:reset")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_37 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_28)
                        .set("parent_ids", String.format("%d,%d", id_1, id_28))
                        .set("permission_name", "任务日志")
                        .set("description", "任务日志")
                        .set("permission_string", "sys:schedule:log")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================参数管理===========================================
        Long id_38 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "参数管理")
                        .set("description", "参数管理")
                        .set("permission_string", "sys:parameter:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_39 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_38)
                        .set("parent_ids", String.format("%d,%d", id_1, id_38))
                        .set("permission_name", "查看参数")
                        .set("description", "查看参数")
                        .set("permission_string", "sys:parameter:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_40 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_38)
                        .set("parent_ids", String.format("%d,%d", id_1, id_38))
                        .set("permission_name", "修改参数")
                        .set("description", "修改参数")
                        .set("permission_string", "sys:parameter:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================登录日志管理===========================================
        Long id_41 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "登录日志")
                        .set("description", "登录日志")
                        .set("permission_string", "sys:loginlog:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_42 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_41)
                        .set("parent_ids", String.format("%d,%d", id_1, id_41))
                        .set("permission_name", "查看日志")
                        .set("description", "查看日志")
                        .set("permission_string", "sys:loginlog:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================操作日志管理===========================================
        Long id_43 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_1)
                        .set("parent_ids", String.format("%d", id_1))
                        .set("permission_name", "操作日志")
                        .set("description", "操作日志")
                        .set("permission_string", "sys:operatelog:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_44 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_43)
                        .set("parent_ids", String.format("%d,%d", id_1, id_43))
                        .set("permission_name", "查看日志")
                        .set("description", "查看日志")
                        .set("permission_string", "sys:operatelog:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================内容管理===========================================
        Long id_45 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", 0)
                        .set("parent_ids", "")
                        .set("permission_name", "内容管理")
                        .set("description", "内容管理")
                        .set("permission_string", "cms:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================文章管理===========================================
        Long id_46 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_45)
                        .set("parent_ids", String.format("%d", id_45))
                        .set("permission_name", "文章管理")
                        .set("description", "文章管理")
                        .set("permission_string", "cms:article:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_47 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_46)
                        .set("parent_ids", String.format("%d,%d", id_45, id_46))
                        .set("permission_name", "查看文章")
                        .set("description", "查看文章")
                        .set("permission_string", "cms:article:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_48 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_46)
                        .set("parent_ids", String.format("%d,%d", id_45, id_46))
                        .set("permission_name", "添加文章")
                        .set("description", "查看文章")
                        .set("permission_string", "cms:article:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_49 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_46)
                        .set("parent_ids", String.format("%d,%d", id_45, id_46))
                        .set("permission_name", "修改文章")
                        .set("description", "修改文章")
                        .set("permission_string", "cms:article:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_50 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_46)
                        .set("parent_ids", String.format("%d,%d", id_45, id_46))
                        .set("permission_name", "删除文章")
                        .set("description", "删除文章")
                        .set("permission_string", "cms:article:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        // ==============================文章分类管理===========================================
        Long id_51 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_45)
                        .set("parent_ids", String.format("%d", id_45))
                        .set("permission_name", "分类管理")
                        .set("description", "分类管理")
                        .set("permission_string", "cms:category:manage")
                        .set("type", 1)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_52 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_51)
                        .set("parent_ids", String.format("%d,%d", id_45, id_51))
                        .set("permission_name", "查看分类")
                        .set("description", "查看文章")
                        .set("permission_string", "cms:category:show")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_53 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_51)
                        .set("parent_ids", String.format("%d,%d", id_45, id_51))
                        .set("permission_name", "添加分类")
                        .set("description", "添加分类")
                        .set("permission_string", "cms:category:create")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_54 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_51)
                        .set("parent_ids", String.format("%d,%d", id_45, id_51))
                        .set("permission_name", "修改分类")
                        .set("description", "修改分类")
                        .set("permission_string", "cms:category:update")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Long id_55 = Db.use().insertForGeneratedKey(
                Entity.create("sys_permission")
                        .set("parent_id", id_51)
                        .set("parent_ids", String.format("%d,%d", id_45, id_51))
                        .set("permission_name", "删除分类")
                        .set("description", "删除分类")
                        .set("permission_string", "cms:category:delete")
                        .set("type", 2)
                        .set("status", 1)
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        List<Long> idList = Arrays.asList(
                id_1, id_2, id_3, id_4, id_5, id_6, id_7, id_8, id_9, id_10,
                id_11, id_12, id_13, id_14, id_15, id_16, id_17, id_18, id_19, id_20,
                id_21, id_22, id_23, id_24, id_25, id_26, id_27, id_28, id_29, id_30,
                id_31, id_32, id_33, id_34, id_35, id_36, id_37, id_38, id_39, id_40,
                id_41, id_42, id_43, id_44, id_45, id_46, id_47, id_48, id_49, id_50,
                id_51, id_52, id_53, id_54, id_55);
        // ==============================角色权限关联表===========================================
        for (Long id : idList) {
            Db.use().insert(
                    Entity.create("sys_role_permission_rel")
                            .set("role_id", rid_1)
                            .set("permission_id", id)
            );
        }

    }

    private static void createDictionary() throws Exception {
        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "status_dict")
                        .set("value", 1)
                        .set("label", "启用")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "数据状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "status_dict")
                        .set("value", 0)
                        .set("label", "停用")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "数据状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "style_dict")
                        .set("value", 0)
                        .set("label", "info")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "按钮样式")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "style_dict")
                        .set("value", 1)
                        .set("label", "success")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "按钮样式")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "style_dict")
                        .set("value", 2)
                        .set("label", "primary")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "按钮样式")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "style_dict")
                        .set("value", 3)
                        .set("label", "warning")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "按钮样式")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "style_dict")
                        .set("value", 4)
                        .set("label", "danger")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "按钮样式")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "perm_dict")
                        .set("value", 1)
                        .set("label", "菜单")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "权限类型")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "perm_dict")
                        .set("value", 2)
                        .set("label", "按钮")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "权限类型")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "job_status_dict")
                        .set("value", 1)
                        .set("label", "任务运行中")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "任务启动状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "job_status_dict")
                        .set("value", 0)
                        .set("label", "任务暂停中")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "任务启动状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "job_result_status_dict")
                        .set("value", 1)
                        .set("label", "执行成功")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "任务执行状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "job_result_status_dict")
                        .set("value", 0)
                        .set("label", "执行失败")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "任务执行状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "publish_status_dict")
                        .set("value", 1)
                        .set("label", "草稿")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章发布状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "publish_status_dict")
                        .set("value", 2)
                        .set("label", "待审核")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章发布状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "publish_status_dict")
                        .set("value", 3)
                        .set("label", "已发布")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章发布状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "recommend_dict")
                        .set("value", 1)
                        .set("label", "已推荐")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章推荐状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "recommend_dict")
                        .set("value", 0)
                        .set("label", "未推荐")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章推荐状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "auto_publish_status_dict")
                        .set("value", 1)
                        .set("label", "是")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章自动发布状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

        Db.use().insert(
                Entity.create("sys_dictionary")
                        .set("code", "auto_publish_status_dict")
                        .set("value", 0)
                        .set("label", "否")
                        .set("sort", 0)
                        .set("status", 1)
                        .set("description", "文章自动发布状态")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );

    }

    private static void createParameter() throws Exception {

        Db.use().insert(
                Entity.create("sys_parameter")
                        .set("param_name", "sys_params")
                        .set("param_value", "{\"copyright\":\"Copyright © 2020 晴天雨\",\"code\":\"<script type=\\\"text/javascript\\\" src=\\\"https://js.users.51.la/19400986.js\\\"></script>\",\"headImgUrl\":\"http://upload.longmarch.top/FuemzbZAVn3PMbcBzrY6IfrtSf_r\",\"keywords\":\"长征CMS系统\",\"defaultUserRole\":\"1\",\"logo\":\"http://q35smspdq.bkt.clouddn.com/Fmn6EGo4ZelbTAJCuDxUbvS1P3gH\",\"description\":\"长征CMS系统，用户管理后台系统\",\"title\":\"长征CMS系统\",\"url\":\"http://www.longmarch.top\"}")
                        .set("remark", "系统默认参数")
        );

        Db.use().insert(
                Entity.create("sys_parameter")
                        .set("param_name", "qiniu_upload")
                        .set("param_value", "{\"bucket\":\"longmarch123\",\"secretKey\":\"ZKj7MXTZUfOnPeuw4hEd23-MZEvXuRc62t02Vddu\",\"accessKey\":\"JP30SdrdnwHXrCO5v24JoEDmBM2mhIU3MndHVqlR\",\"fileMaxSize\":\"20971520\",\"name\":\"文件上传参数\",\"downloadUrl\":\"http://upload.longmarch.top\",\"expireSeconds\":\"1000000\"}")
                        .set("remark", "七牛上传参数")
        );

        Db.use().insert(
                Entity.create("sys_parameter")
                        .set("param_name", "default_user_role")
                        .set("param_value", "{\"roleId\":\"1\"}")
                        .set("remark", "新用户注册默认归属角色")
        );

    }

    private static void createScheduleJob() throws Exception {
        Db.use().insert(
                Entity.create("schedule_job")
                        .set("bean_name", "longMarchJobTask")
                        .set("method_name", "batchPublishArticles")
                        .set("params", "")
                        .set("cron_expression", "1 * * * * ?")
                        .set("status", 0)
                        .set("count", 3)
                        .set("remark", "定时发布文章")
                        .set("create_by", 1)
                        .set("create_time", new Date())
        );
    }

}
