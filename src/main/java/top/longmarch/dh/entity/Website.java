package top.longmarch.dh.entity;

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
 * 导航网址
 * </p>
 *
 * @author YuYue
 * @since 2020-06-22
 */
@Data
@TableName("dh_website")
@ApiModel(value="Website对象", description="导航网址")
public class Website implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "website_id", type = IdType.AUTO)
    private Long websiteId;

    @ApiModelProperty(value = "分类ID")
    @TableField("classify_id")
    private Long classifyId;

    @ApiModelProperty(value = "网站名称")
    @TableField("website_name")
    private String websiteName;

    @ApiModelProperty(value = "网站URL")
    @TableField("website_url")
    private String websiteUrl;

    @ApiModelProperty(value = "网站描述")
    @TableField("website_desc")
    private String websiteDesc;

    @ApiModelProperty(value = "网站排序")
    @TableField("website_sort")
    private Integer websiteSort;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}
