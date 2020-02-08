package top.longmarch.core.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.longmarch.sys.entity.User;

public class UserUtil {

    private static final Logger log = LoggerFactory.getLogger(UserUtil.class);

    public static Subject getSubject() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                return subject;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static User loginUser() {
        return getSubject() == null ? null : (User) getSubject().getPrincipals().getPrimaryPrincipal();
    }

    public static String getUsername() {
        return loginUser() == null ? null : loginUser().getUsername();
    }

}
