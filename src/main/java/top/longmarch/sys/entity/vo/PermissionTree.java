package top.longmarch.sys.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
public class PermissionTree extends Tree<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父节点IDS")
    private String pids;

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

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "路由名字")
    private String name;

    @ApiModelProperty(value = "路由标题")
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否隐藏")
    private Integer hidden;

    @ApiModelProperty(value = "是否缓存")
    private Integer cache;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否已选择")
    private Boolean checked;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
