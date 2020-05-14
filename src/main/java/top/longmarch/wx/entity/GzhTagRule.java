package top.longmarch.wx.entity;

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
 * 规则
 * </p>
 *
 * @author YuYue
 * @since 2020-05-12
 */
@Data
@TableName("wx_gzh_tag_rule")
@ApiModel(value="GzhTagRule对象", description="规则")
public class GzhTagRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "规则ID")
    @TableField("rid")
    private String rid;

    @ApiModelProperty(value = "分数")
    @TableField("score")
    private Integer score;

    @ApiModelProperty(value = "计算（大于或小于）")
    @TableField("compute")
    private String compute;

    @ApiModelProperty(value = "标签内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "新标签ID")
    @TableField("tag_id")
    private Long tagId;

    @ApiModelProperty(value = "公众号ID")
    @TableField("gzh_id")
    private Long gzhId;

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

}
