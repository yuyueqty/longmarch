package top.longmarch.sys.entity.dto;

import lombok.Data;
import top.longmarch.sys.entity.Permission;
import top.longmarch.sys.entity.Role;
import top.longmarch.sys.entity.User;

@Data
public class ChangeStatusDTO {

    private Long id;
    private Integer status;

    public User convertUser() {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        return user;
    }

    public Role convertRole() {
        Role role = new Role();
        role.setId(id);
        role.setStatus(status);
        return role;
    }

    public Permission convertPermission() {
        Permission permission = new Permission();
        permission.setId(id);
        permission.setStatus(status);
        return permission;
    }

}
