package top.longmarch.job.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author YuYue
 * @since 2019-04-07
 */
@Data
@TableName("schedule_job")
@ApiModel(value = "ScheduleJob对象", description = "定时任务")
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "bean名称")
    @TableField("bean_name")
    private String beanName;

    @ApiModelProperty(value = "方法名称")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty(value = "参数")
    @TableField("params")
    private String params;

    @ApiModelProperty(value = "表达式")
    @TableField("cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "0 暂停， 1 恢复")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty(value = "失败尝试次数")
    @TableField("count")
    private Integer count;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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

}
