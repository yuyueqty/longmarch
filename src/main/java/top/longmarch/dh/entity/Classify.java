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

/**
 * <p>
 * 导航分类
 * </p>
 *
 * @author YuYue
 * @since 2020-06-22
 */
@TableName("dh_classify")
@ApiModel(value="Classify对象", description="导航分类")
public class Classify implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "classify_id", type = IdType.AUTO)
    private Long classifyId;

    @ApiModelProperty(value = "分类名称")
    @TableField("classify_name")
    private String classifyName;

    @ApiModelProperty(value = "分类描述")
    @TableField("classify_desc")
    private String classifyDesc;

    @ApiModelProperty(value = "分类排序")
    @TableField("classify_sort")
    private Integer classifySort;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }
    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }
    public String getClassifyDesc() {
        return classifyDesc;
    }

    public void setClassifyDesc(String classifyDesc) {
        this.classifyDesc = classifyDesc;
    }
    public Integer getClassifySort() {
        return classifySort;
    }

    public void setClassifySort(Integer classifySort) {
        this.classifySort = classifySort;
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
        return "Classify{" +
            "classifyId=" + classifyId +
            ", classifyName=" + classifyName +
            ", classifyDesc=" + classifyDesc +
            ", classifySort=" + classifySort +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
        "}";
    }
}
