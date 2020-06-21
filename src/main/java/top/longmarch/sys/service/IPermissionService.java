package top.longmarch.sys.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
public interface IPermissionService extends IService<Permission> {

    JSONObject getPermissionTree();

    void removePermissionByIds(List<Long> ids);

    List<Long> getParentIds(Long id);
}
