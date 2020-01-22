package top.longmarch.sys.service.impl;

import top.longmarch.sys.entity.OperateLog;
import top.longmarch.sys.dao.OperateLogDao;
import top.longmarch.sys.service.IOperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-14
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogDao, OperateLog> implements IOperateLogService {

}
