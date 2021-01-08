package top.longmarch.sys.entity;

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
 * 字典信息
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Data
@TableName("sys_dictionary")
@ApiModel(value="Dictionary对象", description="字典信息")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "字典类型")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "数据值")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "标签名")
    @TableField("label")
    private String label;

    @ApiModelProperty(value = "字典排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "状态（1 停用， 0 启用， 默认 0）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

}
