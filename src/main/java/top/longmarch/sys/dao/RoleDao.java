package top.longmarch.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.longmarch.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.sys.entity.vo.PermissionTree;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface RoleDao extends BaseMapper<Role> {

    IPage<Role> search(Page page, Map<String, Object> params);

    List<PermissionTree> selectRolePermissionById(Long roleId);

}
