package top.longmarch.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import top.longmarch.sys.entity.Role;

import java.util.List;

@Data
public class CreateUpdateRoleDTO {

    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "状态（0 停用， 1 启用， 默认 1）")
    private Integer status;

    @ApiModelProperty(value = "数据权限（1 用户ID， 2 部门ID， 3 全部，默认 1）")
    private Integer dataPerm;

    @ApiModelProperty(value = "数据权限ID集合")
    private String dataPermIds;

    private List<Long> checkedKeys;

    public Role convertRole() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }

}
