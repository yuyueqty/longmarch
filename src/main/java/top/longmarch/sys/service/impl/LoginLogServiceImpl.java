package top.longmarch.sys.service.impl;

import top.longmarch.sys.entity.LoginLog;
import top.longmarch.sys.dao.LoginLogDao;
import top.longmarch.sys.service.ILoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLog> implements ILoginLogService {

}
