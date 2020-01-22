package top.longmarch.core.utils;//package top.longmarch.core.utils;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.longmarch.sys.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtil {

    private static final Logger log = LoggerFactory.getLogger(UserUtil.class);

    public static User loginUser() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            if (subject.isAuthenticated()) {
                return (User) subject.getPrincipals().getPrimaryPrincipal();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}
