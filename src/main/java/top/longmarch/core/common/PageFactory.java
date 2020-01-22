package top.longmarch.core.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

public class PageFactory {
    /**
     * 每页显示条数，默认 10
     */
    public static String SIZE_KEY = "size";
    public static long SIZE_VALUE = 10;
    /**
     * 当前页
     */
    public static String CURRENT_KEY = "current";
    public static long CURRENT_VALUE = 1;

    public static Map<String, Object> buildMap(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
    public static <T> Page<T> getInstance(Map<String, Object> params) {
        params = buildMap(params);
        long current = params.get(CURRENT_KEY) == null ? CURRENT_VALUE : Long.valueOf(params.get(CURRENT_KEY).toString());
        long size = params.get(SIZE_KEY) == null ? SIZE_VALUE : Long.valueOf(params.get(SIZE_KEY).toString());
        return new Page(current, size);
    }

}
