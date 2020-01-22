package top.longmarch.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    public String getOperateDetail() {
        return operateDetail;
    }

    public void setOperateDetail(String operateDetail) {
        this.operateDetail = operateDetail;
    }
    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    @Override
    public String toString() {
        return "OperateLog{" +
            "id=" + id +
            ", userId=" + userId +
            ", userName=" + userName +
            ", busType=" + busType +
            ", operateType=" + operateType +
            ", operateDetail=" + operateDetail +
            ", operateTime=" + operateTime +
        "}";
    }
}
