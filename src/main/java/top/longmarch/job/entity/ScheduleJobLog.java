package top.longmarch.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-04-07
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ScheduleJobLog{" +
                "id=" + id +
                "jobId=" + jobId +
                ", beanName='" + beanName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", error='" + error + '\'' +
                ", status=" + status +
                ", executeTime=" + executeTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
