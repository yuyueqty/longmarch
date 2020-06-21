package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 权限信息
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Data
@TableName("sys_permission")
@ApiModel(value="Permission对象", description="权限信息")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父节点ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "权限名称")
    @TableField("parent_ids")
    private String parentIds;

    @ApiModelProperty(value = "权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "权限字符串")
    @TableField("permission_string")
    private String permissionString;

    @ApiModelProperty(value = "权限类型，菜单 1，按钮 2")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "状态（1 停用， 0 启用， 默认 0）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "路径")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "重定向")
    @TableField("redirect")
    private String redirect;

    @ApiModelProperty(value = "路由名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "路由标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "是否隐藏")
    @TableField("hidden")
    private Integer hidden;

    @ApiModelProperty(value = "是否缓存")
    @TableField("cache")
    private Integer cache;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

}
