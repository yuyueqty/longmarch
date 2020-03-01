package top.longmarch.core.aspect;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.sys.entity.LoginLog;
import top.longmarch.sys.entity.OperateLog;
import top.longmarch.sys.service.ILoginLogService;
import top.longmarch.sys.service.IOperateLogService;
import top.longmarch.sys.service.IUserService;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private ILoginLogService loginLogService;
    @Autowired
    private IOperateLogService operateLogService;
    @Autowired
    private IUserService userService;

    @Override
    public void saveOperateLog(Object object) {
        OperateLog operateLog = JSONUtil.toBean(JSONUtil.toJsonStr(object), OperateLog.class);
        operateLogService.save(operateLog);
    }

    @Transactional
    @Override
    public void saveLoginLog(Object object) {
        LoginLog loginLog = JSONUtil.toBean(JSONUtil.toJsonStr(object), LoginLog.class);
        loginLogService.save(loginLog);
        userService.updateUserLoginInfo(loginLog.getUserId());
    }

}
