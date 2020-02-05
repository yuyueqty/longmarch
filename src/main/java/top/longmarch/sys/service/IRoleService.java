package top.longmarch.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.Role;
import top.longmarch.sys.entity.vo.RoleUserDTO;

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

    IPage<Role> search(Map<String, Object> params);

    Map<String, Object> selectRolePermissionById(Long roleId);

    void updateRole(Role role);

    void saveRole(Role role);

    void removeRole(List<Long> asList);

    List<RoleUserDTO> handleLoadRoleUsers(Long roleId, String username);

    void addRoleUsers(Role role);
}
