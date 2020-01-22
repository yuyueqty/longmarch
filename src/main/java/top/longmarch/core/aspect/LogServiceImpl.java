package top.longmarch.core.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.sys.entity.LoginLog;
import top.longmarch.sys.entity.OperateLog;
import top.longmarch.sys.entity.User;
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
        OperateLog OperateLog = JSONUtil.toBean(JSONUtil.toJsonStr(object), OperateLog.class);
        operateLogService.save(OperateLog);
    }

    @Override
    public void saveLoginLog(Object object) {
        LoginLog loginLog = JSONUtil.toBean(JSONUtil.toJsonStr(object), LoginLog.class);
        loginLogService.save(loginLog);
        User userDB = userService.getById(loginLog.getUserId());
        if (userDB != null) {
            User user = new User();
            user.setId(loginLog.getUserId());
            user.setLastLoginTime(loginLog.getLoginTime());
            user.setLoginCount(userDB.getLoginCount()+1);
            userService.updateById(user);
        }
    }

}
