package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@Data
@TableName("sys_operate_log")
@ApiModel(value="OperateLog对象", description="操作日志")
public class OperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "模块名称")
    @TableField("bus_type")
    private String busType;

    @ApiModelProperty(value = "操作类型")
    @TableField("operate_type")
    private String operateType;

    @ApiModelProperty(value = "操作详情")
    @TableField("operate_detail")
    private String operateDetail;

    @ApiModelProperty(value = "操作时间")
    @TableField("operate_time")
    private Date operateTime;

}
