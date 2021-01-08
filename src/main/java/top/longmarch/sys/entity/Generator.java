package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@Data
@TableName("sys_generator")
@ApiModel(value="SysGenerator对象", description="生成工具实体")
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

}
