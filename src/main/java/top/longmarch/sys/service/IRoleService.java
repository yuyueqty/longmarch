package top.longmarch.sys.service;

import top.longmarch.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.vo.PermissionTree;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> selectRolePermissionById(Long roleId);

    void updateRole(Role role);

    void saveRole(Role role);

    void removeRole(List<Long> asList);
}
