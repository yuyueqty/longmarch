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

/**
 * <p>
 * 文章
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
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

    @ApiModelProperty(value = "发布状态（0 未发布， 1 审核中，2 已发布，默认 0）")
    @TableField("publish_status")
    private Integer publishStatus;

    @ApiModelProperty(value = "点击次数（默认0）")
    @TableField("clicks")
    private Integer clicks;

    @ApiModelProperty(value = "点赞次数（默认0）")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "文章排序（由大到小排序，默认0）")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "是否置顶（0 否， 1 是，默认 0）")
    @TableField("is_top")
    private Boolean top;

    @ApiModelProperty(value = "是否删除（1 已删除，0 未删除，默 0）")
    @TableField("is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "租户编号")
    @TableField("tenant_id")
    private Long tenantId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }
    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", categoryId=" + categoryId +
            ", title=" + title +
            ", summary=" + summary +
            ", content=" + content +
            ", sourceUrl=" + sourceUrl +
            ", imageUrl=" + imageUrl +
            ", author=" + author +
            ", publishTime=" + publishTime +
            ", publishStatus=" + publishStatus +
            ", clicks=" + clicks +
            ", likes=" + likes +
            ", sort=" + sort +
            ", top=" + top +
            ", deleted=" + deleted +
            ", tenantId=" + tenantId +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
        "}";
    }
}
