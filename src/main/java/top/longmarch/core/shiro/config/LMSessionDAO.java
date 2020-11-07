package top.longmarch.core.shiro.config;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import top.longmarch.core.common.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class LMSessionDAO extends AbstractSessionDAO {

    private CacheManager cacheManager;

    public LMSessionDAO(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = this.generateSessionId(session);
        assignSessionId(session, serializable);
        this.cache().put(serializable, session);
        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Object session = this.cache().get(sessionId);
        if (session == null) {
            return null;
        }
        return (Session) session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.cache().put(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        this.cache().remove(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Object> values = this.cache().values();
        Collection<Session> sessions = new ArrayList<>();
        if (values == null || values.size() == 0) {
            return sessions;
        }
        for (Object session : values) {
            sessions.add((Session) session);
        }
        return sessions;
    }

    private Cache<Object, Object> cache() {
        return cacheManager.getCache(Constant.ACTIVE_SESSION_CACHE);
    }

}
