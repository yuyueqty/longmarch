package top.longmarch.sys.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import top.longmarch.core.utils.tree.Tree;

import java.util.Date;

/**
 * <p>
 * 权限树信息
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public class PermissionTree extends Tree<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限描述")
    private String description;

    @ApiModelProperty(value = "权限字符串")
    private String permissionString;

    @ApiModelProperty(value = "权限类型，菜单 1，按钮 2")
    private Integer type;

    @ApiModelProperty(value = "状态（1 停用， 0 启用， 默认 0）")
    private Integer status;

    @ApiModelProperty(value = "是否已选择")
    private Boolean checked;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermissionString() {
        return permissionString;
    }

    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PermissionTree{" +
                "permissionName='" + permissionName + '\'' +
                ", description='" + description + '\'' +
                ", permissionString='" + permissionString + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", checked=" + checked +
                ", createTime=" + createTime +
                '}';
    }
}
