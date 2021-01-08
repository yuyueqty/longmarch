package top.longmarch.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-04-07
 */
@Data
@TableName("schedule_job_log")
@ApiModel(value = "ScheduleJobLog对象", description = "定时任务日志")
public class ScheduleJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务ID")
    @TableField("job_id")
    private Long jobId;

    @ApiModelProperty(value = "bean名称")
    @TableField("bean_name")
    private String beanName;

    @ApiModelProperty(value = "方法名称")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "失败信息")
    @TableField("error")
    private String error;

    @ApiModelProperty(value = "任务状态（0 未执行，1 成功， 2 失败）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "执行时间（毫秒）")
    @TableField("execute_time")
    private Long executeTime;

    @ApiModelProperty(value = "任务开始执行时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "任务执行结束时间")
    @TableField("end_time")
    private Date endTime;

}
