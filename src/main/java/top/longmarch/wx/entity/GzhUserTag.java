//package top.longmarch.wx.entity;
//
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.baomidou.mybatisplus.annotation.IdType;
//import java.util.Date;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
//import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//
///**
// * <p>
// * 粉丝标签表
// * </p>
// *
// * @author YuYue
// * @since 2020-04-19
// */
//@TableName("wx_gzh_user_tag")
//@ApiModel(value="GzhUserTag对象", description="粉丝标签表")
//public class GzhUserTag implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @ApiModelProperty(value = "主键")
//    @TableId(value = "id", type = IdType.AUTO)
//    private Long id;
//
//    @ApiModelProperty(value = "公众号ID")
//    @TableField("gzh_id")
//    private Long gzhId;
//
//    @ApiModelProperty(value = "分维标签行业ID")
//    @TableField("industry_id")
//    private Long industryId;
//
//    @ApiModelProperty(value = "分维标签名称")
//    @TableField("fenwei_name")
//    private String fenweiName;
//
//    @ApiModelProperty(value = "阈值")
//    @TableField("threshold")
//    private Integer threshold;
//
//    @ApiModelProperty(value = "可信度（低中高）")
//    @TableField("reliability")
//    private String reliability;
//
//    @ApiModelProperty(value = "微信标签id")
//    @TableField("tagid")
//    private Long tagid;
//
//    @ApiModelProperty(value = "标签名称")
//    @TableField("name")
//    private String name;
//
//    @ApiModelProperty(value = "标签归属公众号原始id")
//    @TableField("jwid")
//    private String jwid;
//
//    @ApiModelProperty(value = "此标签下粉丝数")
//    @TableField("count")
//    private Integer count;
//
//    @ApiModelProperty(value = "同步时间")
//    @TableField("synctime")
//    private Date synctime;
//
//    @ApiModelProperty(value = "是否删除（1 已删除，0 未删除，默 0）")
//    @TableField("is_deleted")
//    private Boolean deleted;
//
//    @ApiModelProperty(value = "租户编号")
//    @TableField("tenant_id")
//    private Long tenantId;
//
//    @ApiModelProperty(value = "创建者ID")
//    @TableField(value = "create_by", fill = FieldFill.INSERT)
//    private Long createBy;
//
//    @ApiModelProperty(value = "创建时间")
//    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新ID")
//    @TableField(value = "update_by", fill = FieldFill.UPDATE)
//    private Long updateBy;
//
//    @ApiModelProperty(value = "更新时间")
//    @TableField(value = "update_time", fill = FieldFill.UPDATE)
//    private Date updateTime;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public Long getGzhId() {
//        return gzhId;
//    }
//
//    public void setGzhId(Long gzhId) {
//        this.gzhId = gzhId;
//    }
//    public Long getIndustryId() {
//        return industryId;
//    }
//
//    public void setIndustryId(Long industryId) {
//        this.industryId = industryId;
//    }
//    public String getFenweiName() {
//        return fenweiName;
//    }
//
//    public void setFenweiName(String fenweiName) {
//        this.fenweiName = fenweiName;
//    }
//    public Integer getThreshold() {
//        return threshold;
//    }
//
//    public void setThreshold(Integer threshold) {
//        this.threshold = threshold;
//    }
//    public String getReliability() {
//        return reliability;
//    }
//
//    public void setReliability(String reliability) {
//        this.reliability = reliability;
//    }
//    public Long getTagid() {
//        return tagid;
//    }
//
//    public void setTagid(Long tagid) {
//        this.tagid = tagid;
//    }
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getJwid() {
//        return jwid;
//    }
//
//    public void setJwid(String jwid) {
//        this.jwid = jwid;
//    }
//    public Integer getCount() {
//        return count;
//    }
//
//    public void setCount(Integer count) {
//        this.count = count;
//    }
//    public Date getSynctime() {
//        return synctime;
//    }
//
//    public void setSynctime(Date synctime) {
//        this.synctime = synctime;
//    }
//    public Boolean getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Boolean deleted) {
//        this.deleted = deleted;
//    }
//    public Long getTenantId() {
//        return tenantId;
//    }
//
//    public void setTenantId(Long tenantId) {
//        this.tenantId = tenantId;
//    }
//    public Long getCreateBy() {
//        return createBy;
//    }
//
//    public void setCreateBy(Long createBy) {
//        this.createBy = createBy;
//    }
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//    public Long getUpdateBy() {
//        return updateBy;
//    }
//
//    public void setUpdateBy(Long updateBy) {
//        this.updateBy = updateBy;
//    }
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    @Override
//    public String toString() {
//        return "GzhUserTag{" +
//            "id=" + id +
//            ", gzhId=" + gzhId +
//            ", industryId=" + industryId +
//            ", fenweiName=" + fenweiName +
//            ", threshold=" + threshold +
//            ", reliability=" + reliability +
//            ", tagid=" + tagid +
//            ", name=" + name +
//            ", jwid=" + jwid +
//            ", count=" + count +
//            ", synctime=" + synctime +
//            ", deleted=" + deleted +
//            ", tenantId=" + tenantId +
//            ", createBy=" + createBy +
//            ", createTime=" + createTime +
//            ", updateBy=" + updateBy +
//            ", updateTime=" + updateTime +
//        "}";
//    }
//}
