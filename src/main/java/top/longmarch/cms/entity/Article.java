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
 * 文章
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
@Data
@TableName("cms_article")
@ApiModel(value="Article对象", description="文章")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类目ID")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "摘要")
    @TableField("summary")
    private String summary;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "来源地址")
    @TableField("source_url")
    private String sourceUrl;

    @ApiModelProperty(value = "主图片地址")
    @TableField("image_url")
    private String imageUrl;

    @ApiModelProperty(value = "文章作者")
    @TableField("author")
    private String author;

    @ApiModelProperty(value = "发布时间")
    @TableField("publish_time")
    private Date publishTime;

    @ApiModelProperty(value = "发布状态（1 未发布， 2 审核中，3 已发布，默认 1）")
    @TableField("publish_status")
    private Integer publishStatus;

    @ApiModelProperty(value = "是否自动发布（0 否，1 是，默认 0）")
    @TableField("auto_publish_status")
    private Integer autoPublishStatus;

    @ApiModelProperty(value = "点击次数（默认0）")
    @TableField("clicks")
    private Integer clicks;

    @ApiModelProperty(value = "点赞次数（默认0）")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "文章排序（由大到小排序，默认0）")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "是否推荐（0 否， 1 是，默认 0）")
    @TableField("recommend")
    private Integer recommend;

    @ApiModelProperty(value = "文章标签")
    @TableField("tags")
    private String tags;

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

    @ApiModelProperty(value = "分类名称")
    @TableField(exist = false)
    private String categoryName;

}
