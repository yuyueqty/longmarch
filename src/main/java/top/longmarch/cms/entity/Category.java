package top.longmarch.cms.entity;

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
 * 文章类目
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
@Data
@TableName("cms_category")
@ApiModel(value="Category对象", description="文章类目")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类目名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "父分类ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "跳转地址")
    @TableField("redirect_url")
    private String redirectUrl;

    @ApiModelProperty(value = "类目图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "类目排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "启用状态（0 未启用，1 已启用，默认 1）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "分类详情")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建者ID")
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
