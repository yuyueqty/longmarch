package top.longmarch.sys.dao;

import top.longmarch.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.sys.entity.vo.PermissionTree;

import java.util.List;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface RoleDao extends BaseMapper<Role> {

    List<PermissionTree> selectRolePermissionById(Long roleId);

}
