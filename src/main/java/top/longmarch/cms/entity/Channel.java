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
 * 频道
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@TableName("cms_channel")
@ApiModel(value="Channel对象", description="频道")
public class Channel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "频道名称")
    @TableField("channel_name")
    private String channelName;

    @ApiModelProperty(value = "频道编号")
    @TableField("channel_code")
    private String channelCode;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "频道图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "频道排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "启用状态（0 未启用，1 已启用，默认 1）")
    @TableField("status")
    private Boolean status;

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
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Channel{" +
            "id=" + id +
            ", channelName=" + channelName +
            ", channelCode=" + channelCode +
            ", remark=" + remark +
            ", icon=" + icon +
            ", sort=" + sort +
            ", status=" + status +
            ", tenantId=" + tenantId +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
        "}";
    }
}
