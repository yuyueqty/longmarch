package top.longmarch.wx.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 规则
 * </p>
 *
 * @author YuYue
 * @since 2020-05-09
 */
@TableName("wx_fwtag_rule")
@ApiModel(value = "FwtagRule对象", description = "规则")
public class FwtagRule implements Serializable {

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

    @ApiModelProperty(value = "新标签")
    @TableField("new_tag")
    private String newTag;

    @ApiModelProperty(value = "公众号ID")
    @TableField("gzh_id")
    private Integer gzhId;

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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGzhId() {
        return gzhId;
    }

    public void setGzhId(Integer gzhId) {
        this.gzhId = gzhId;
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

    public String getNewTag() {
        return newTag;
    }

    public void setNewTag(String newTag) {
        this.newTag = newTag;
    }

    public String getCompute() {
        return compute;
    }

    public void setCompute(String compute) {
        this.compute = compute;
    }

    @Override
    public String toString() {
        return "FwtagRule{" +
                "id=" + id +
                ", rid=" + rid +
                ", score=" + score +
                ", compute=" + compute +
                ", content=" + content +
                ", newTag=" + newTag +
                ", gzhId=" + gzhId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                "}";
    }
}
