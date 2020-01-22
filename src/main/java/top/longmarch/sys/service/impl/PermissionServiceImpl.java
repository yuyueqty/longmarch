package top.longmarch.sys.service.impl;

import top.longmarch.sys.entity.Permission;
import top.longmarch.sys.dao.PermissionDao;
import top.longmarch.sys.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限信息 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements IPermissionService {

}
