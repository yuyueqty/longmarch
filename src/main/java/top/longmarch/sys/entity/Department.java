package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 部门信息
 * </p>
 *
 * @author YuYue
 * @since 2020-02-05
 */
@TableName("sys_department")
@ApiModel(value="Department对象", description="部门信息")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父级ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "父级IDS")
    @TableField("parent_ids")
    private String parentIds;

    @ApiModelProperty(value = "部门名称")
    @TableField("dep_name")
    private String depName;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @TableField(exist = false)
    private Integer userCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + id +
            ", parentId=" + parentId +
            ", parentIds=" + parentIds +
            ", depName=" + depName +
            ", sort=" + sort +
            ", userCount=" + userCount +
        "}";
    }
}
