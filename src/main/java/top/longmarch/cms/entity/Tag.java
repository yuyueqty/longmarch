package top.longmarch.cms.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author YuYue
 * @since 2020-02-27
 */
@Data
@TableName("cms_tag")
@ApiModel(value = "Tag对象", description = "标签")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标签名称")
    @TableField("tag_name")
    private String tagName;

    @ApiModelProperty(value = "标签文章数量")
    @TableField("num")
    private Integer num;

    @ApiModelProperty(value = "标签热度")
    @TableField("hot")
    private Integer hot;

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
