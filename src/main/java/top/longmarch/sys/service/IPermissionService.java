package top.longmarch.sys.service;

import top.longmarch.sys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface IPermissionService extends IService<Permission> {

    Map<String, Object> getPermissionTree();

    void updatePermissionById(Permission permission);

    void savePermission(Permission permission);

    void removePermissionByIds(List<Long> ids);
}
