package top.longmarch.core.common;

public class Constant {

    // 分页相关常量
    public static final String CURRENT_KEY = "current";
    public static final String SIZE_KEY = "size";
    public static final String CURRENT_VALUE = "1";
    public static final String SIZE_VALUE = "10";
    public static final String FUZZY_SEARCH = "fuzzySearch";
    public static final String STATUS = "status";
    public static final String PROP = "prop";
    public static final String ORDER = "order";

    // 系统参数相关常量
    public static final String QINIU_UPLOAD = "qiniu_upload";
    public static final String DEFAULT_USER_ROLE = "default_user_role";
    public static final String SYS_PARAMS = "sys_params";
    public static final String PROJECT_PATH = System.getProperty("java.io.tmpdir") + "/soundcode";

    // 数据权限类型
    public static final Integer ONE_USER = 1;
    public static final Integer MORE_USER = 2;
    public static final Integer All_USER = 3;

    // 菜单/按钮
    public static final Integer MENU = 1;
    public static final Integer BUTTON = 2;

    // 默认SESSION超时时间：1小时=3600000毫秒(ms)
//    public static final long SESSION_TIMEOUT = 1000 * 60 * 60 * 24;
    public static final long SESSION_TIMEOUT = 1000 * 60 * 2;
    // 10分钟清理一次失效的Session
//    public static final long SESSION_CLEAR_TILE = 1000 * 60 * 10;
    public static final long SESSION_CLEAR_TILE = 1000 * 60;
    // Cache
    public static final String ACTIVE_SESSION_CACHE = "__activeSessionCache__";
    public static final String KEEP_ONE_USER_CACHE = "__keepOneUserCache__";
    public static final String AUTHENTICATION_CACHE = "__authenticationCache__";
    public static final String AUTHORIZATION_CACHE = "__authorizationCache__";
    public static final String USER_PERMISSION_CACHE = "UserIRolePermissionCacheService";
    public static final String ACTIVITY_USER_INFO_KEY = "activity_user_info_%s";

}
