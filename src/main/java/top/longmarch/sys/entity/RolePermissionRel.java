package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 角色权限关联信息
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@TableName("sys_role_permission_rel")
@ApiModel(value="RolePermissionRel对象", description="角色权限关联信息")
public class RolePermissionRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "权限ID")
    @TableField("permission_id")
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionRel{" +
            "roleId=" + roleId +
            ", permissionId=" + permissionId +
        "}";
    }
}
