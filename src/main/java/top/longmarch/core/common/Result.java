package top.longmarch.core.common;

import java.util.HashMap;

/**
 * 响应封装
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 6272655632566784880L;

    // 成功响应
    public static final int RESPOND_SUCCEED_CODE = 1000;
    public static final String RESPOND_SUCCEED_MESSAGE = "succeed";
    // 失败响应
    public static final int RESPOND_FAIL_CODE = 5000;
    public static final String RESPOND_FAIL_MESSAGE = "fail";

    public static Result build() {
        return new Result();
    }

    public static Result ok() {
        Result baseReturn = new Result();
        baseReturn.put("code", RESPOND_SUCCEED_CODE);
        baseReturn.put("message", RESPOND_SUCCEED_MESSAGE);
        return baseReturn;
    }

    public static Result ok(String message) {
        Result baseReturn = new Result();
        baseReturn.put("code", RESPOND_SUCCEED_CODE);
        baseReturn.put("message", message);
        return baseReturn;
    }

    public static Result fail() {
        Result baseReturn = new Result();
        baseReturn.put("code", RESPOND_FAIL_CODE);
        baseReturn.put("message", RESPOND_FAIL_MESSAGE);
        return baseReturn;
    }

    public static Result fail(String message) {
        Result baseReturn = new Result();
        baseReturn.put("code", RESPOND_FAIL_CODE);
        baseReturn.put("message", message);
        return baseReturn;
    }

    public static Result fail(Integer code, String message) {
        Result baseReturn = new Result();
        baseReturn.put("code", code);
        baseReturn.put("message", message);
        return baseReturn;
    }

    public Result respond(int respond) {
        this.put("code", respond);
        return this;
    }

    public Result message(String message) {
        this.put("message", message);
        return this;
    }

    public Result add(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public Result add(Object value) {
        this.put("data", value);
        return this;
    }

}
