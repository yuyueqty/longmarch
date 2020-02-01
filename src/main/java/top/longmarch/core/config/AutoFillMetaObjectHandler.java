package top.longmarch.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.longmarch.core.utils.UserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 注入公共字段自动填充,任选注入方式即可
 */
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    private final static Logger logger = LoggerFactory.getLogger(AutoFillMetaObjectHandler.class);
    private static List<String> tableList = new ArrayList<String>();

    static {
        tableList.add("RolePermissionRel");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        String simpleName = metaObject.getOriginalObject().getClass().getSimpleName();
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (Exception e) {
        }
        if (subject != null && subject.isAuthenticated() && !tableList.contains(simpleName)) {
            logger.info("start insert fill ....");
            this.strictInsertFill(metaObject, "createBy", Long.class, UserUtil.loginUser().getId());
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String simpleName = metaObject.getOriginalObject().getClass().getSimpleName();
        Subject subject = null;
        try {
            subject = SecurityUtils.getSubject();
        } catch (Exception e) {
        }
        if (subject != null && subject.isAuthenticated() && !tableList.contains(simpleName)) {
            logger.info("start update fill ....");
            this.strictUpdateFill(metaObject, "updateBy", Long.class, UserUtil.loginUser().getId());
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        }
    }

}
