package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author YuYue
 * @since 2020-02-19
 */
@TableName("sys_generator")
@ApiModel(value="SysGenerator对象", description="")
public class Generator implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "表名")
    @TableField("table_name")
    private String tableName;

    @ApiModelProperty(value = "字段名")
    @TableField("column_name")
    private String columnName;

    @ApiModelProperty(value = "字段类型")
    @TableField("column_type")
    private String columnType;

    @ApiModelProperty(value = "属性名")
    @TableField("property_name")
    private String propertyName;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "不为空")
    @TableField("not_null")
    private Boolean notNull;

    @ApiModelProperty(value = "列表显示")
    @TableField("list_show")
    private Boolean listShow;

    @ApiModelProperty(value = "表单显示")
    @TableField("form_show")
    private Boolean formShow;

    @ApiModelProperty(value = "表单类型（input, textarea, radio, checkbox, date）")
    @TableField("form_type")
    private String formType;

    @ApiModelProperty(value = "查询类型（eq, date）")
    @TableField("query_type")
    private String queryType;

    @ApiModelProperty(value = "是否排序")
    @TableField("order_by")
    private Boolean orderBy;

    @ApiModelProperty(value = "是否参数")
    @TableField("parameter")
    private Boolean parameter;

    @ApiModelProperty(value = "默认值")
    @TableField("default_value")
    private String defaultValue;

    @ApiModelProperty(value = "字典code")
    @TableField("dict_code")
    private String dictCode;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }
    public Boolean getListShow() {
        return listShow;
    }

    public void setListShow(Boolean listShow) {
        this.listShow = listShow;
    }
    public Boolean getFormShow() {
        return formShow;
    }

    public void setFormShow(Boolean formShow) {
        this.formShow = formShow;
    }
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
    public Boolean getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Boolean orderBy) {
        this.orderBy = orderBy;
    }
    public Boolean getParameter() {
        return parameter;
    }

    public void setParameter(Boolean parameter) {
        this.parameter = parameter;
    }
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SysGenerator{" +
            "id=" + id +
            ", tableName=" + tableName +
            ", columnName=" + columnName +
            ", columnType=" + columnType +
            ", propertyName=" + propertyName +
            ", remark=" + remark +
            ", notNull=" + notNull +
            ", listShow=" + listShow +
            ", formShow=" + formShow +
            ", formType=" + formType +
            ", queryType=" + queryType +
            ", orderBy=" + orderBy +
            ", parameter=" + parameter +
            ", defaultValue=" + defaultValue +
            ", dictCode=" + dictCode +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", updateBy=" + updateBy +
            ", updateTime=" + updateTime +
        "}";
    }
}
