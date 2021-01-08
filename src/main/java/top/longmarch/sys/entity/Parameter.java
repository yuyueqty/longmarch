package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 平台参数表
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@Data
@TableName("sys_parameter")
@ApiModel(value="Parameter对象", description="平台参数表")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "param_id", type = IdType.AUTO)
    private Long paramId;

    @ApiModelProperty(value = "参数名称")
    @TableField("param_name")
    private String paramName;

    @ApiModelProperty(value = "参数值")
    @TableField("param_value")
    private String paramValue;

    @ApiModelProperty(value = "参数描述")
    @TableField("remark")
    private String remark;

}
