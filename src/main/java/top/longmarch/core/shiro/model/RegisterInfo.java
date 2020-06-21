package top.longmarch.core.shiro.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class RegisterInfo implements Serializable {

    private static final String PHONE = "(?:0|86|\\+86)?1[3456789]\\d{9}";

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = PHONE, message = "手机号不正确")
    private String phone;

}
