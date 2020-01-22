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
 * 文章类目
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@TableName("cms_category")
@ApiModel(value="Category对象", description="文章类目")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道ID")
    @TableField("channel_id")
    private Long channelId;

    @ApiModelProperty(value = "类目名称")
    @TableField("category_name")
    private String categoryName;

    @ApiModelProperty(value = "父分类ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "父节点ID集合（多个ID以','分割）")
    @TableField("parent_ids")
    private String parentIds;

    @ApiModelProperty(value = "树形深度")
    @TableField("depth")
    private Integer depth;

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
    private Boolean status;

    @ApiModelProperty(value = "分类详情")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "租户编号")
    @TableField("tenant_id")
    private Long tenantId;

    @ApiModelProperty(value = "创建者ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", channelId=" + channelId +
            ", categoryName=" + categoryName +
            ", parentId=" + parentId +
            ", parentIds=" + parentIds +
            ", depth=" + depth +
            ", redirectUrl=" + redirectUrl +
            ", icon=" + icon +
            ", sort=" + sort +
            ", status=" + status +
            ", remark=" + remark +
            ", tenantId=" + tenantId +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
        "}";
    }
}
