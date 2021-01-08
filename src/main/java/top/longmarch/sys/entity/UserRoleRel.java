package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户角色关联信息
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Data
@TableName("sys_user_role_rel")
@ApiModel(value = "UserRoleRel对象", description = "用户角色关联信息")
public class UserRoleRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

}
