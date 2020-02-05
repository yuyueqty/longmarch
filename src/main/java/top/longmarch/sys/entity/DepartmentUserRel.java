package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author YuYue
 * @since 2020-02-05
 */
@TableName("sys_department_user_rel")
@ApiModel(value = "DepartmentUserRel对象", description = "")
public class DepartmentUserRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门ID")
    @TableField("department_id")
    private Long departmentId;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DepartmentUserRel{" +
                "departmentId=" + departmentId +
                ", userId=" + userId +
                "}";
    }
}
