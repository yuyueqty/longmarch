package top.longmarch.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import top.longmarch.sys.entity.User;

import javax.validation.constraints.NotNull;

@Data
public class CreateUpdateUserDTO {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名称")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String headImgUrl;

    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "状态（1 停用， 0 启用， 默认 0）")
    private Integer status;

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "角色IDS")
    private String roleIds;

    public User convertUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
