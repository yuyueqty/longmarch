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
 * 公众号粉丝分维解析标签
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Data
@TableName("wx_gzh_fenwei_tag")
@ApiModel(value="GzhFenweiTag对象", description="公众号粉丝分维解析标签")
public class GzhFenweiTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公众号ID")
    @TableField("gzh_id")
    private Long gzhId;

    @ApiModelProperty(value = "微信OpenID")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "行业ID")
    @TableField("field_id")
    private Integer fieldId;

    @ApiModelProperty(value = "标签名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "标签内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "可信度")
    @TableField("rank")
    private String rank;

    @ApiModelProperty(value = "阈值")
    @TableField("score")
    private Integer score;

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
