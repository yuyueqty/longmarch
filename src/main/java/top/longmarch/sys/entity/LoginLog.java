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
 * 
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@Data
@TableName("sys_login_log")
@ApiModel(value="LoginLog对象", description="登录日志")
public class LoginLog implements Serializable {

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

    @ApiModelProperty(value = "登陆人ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "登陆时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty(value = "登陆设备信息")
    @TableField("user_agent")
    private String userAgent;

}
