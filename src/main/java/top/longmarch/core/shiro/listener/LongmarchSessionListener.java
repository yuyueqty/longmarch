package top.longmarch.core.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.longmarch.core.config.ApplicationContextManager;
import top.longmarch.sys.service.impl.LMCacheManage;

public class LongmarchSessionListener implements SessionListener {

    private static final Logger log = LoggerFactory.getLogger(LongmarchSessionListener.class);

    @Override
    public void onStart(Session session) {
        log.info("创建Session：{}", session.getId());
    }

    @Override
    public void onStop(Session session) {
        LMCacheManage lmCacheManage = ApplicationContextManager.getBean(LMCacheManage.class);
        lmCacheManage.cleanCacheSession(session.getId().toString());
        log.info("停止Session：{}", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        LMCacheManage lmCacheManage = ApplicationContextManager.getBean(LMCacheManage.class);
        lmCacheManage.cleanCacheSession(session.getId().toString());
        log.info("销毁Session：{}", session.getId());
    }

}
