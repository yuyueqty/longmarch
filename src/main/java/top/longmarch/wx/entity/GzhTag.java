package top.longmarch.wx.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 微信公众号标签
 * </p>
 *
 * @author YuYue
 * @since 2020-05-12
 */
@Data
@TableName("wx_gzh_tag")
@ApiModel(value="GzhTag对象", description="微信公众号标签")
public class GzhTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公众号ID")
    @TableField(value = "gzh_id")
    private Long gzhId;

    @ApiModelProperty(value = "微信公众号标签ID")
    @TableField(value = "wx_tag_id")
    private Long wxTagId;

    @ApiModelProperty(value = "新标签名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建者ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新ID")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private List<GzhTagRule> gzhTagRuleList;
}
