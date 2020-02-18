package top.longmarch.core.utils;

public class LmUtils {

    public static boolean isBlank(Object object) {
        return null == object || "".equals(object) || "null".equals(object);
    }

    public static boolean isNotBlank(Object object) {
        return !isBlank(object);
    }

}
