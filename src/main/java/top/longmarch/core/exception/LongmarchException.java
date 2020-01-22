package top.longmarch.core.exception;

import top.longmarch.core.common.Result;

public class LongmarchException extends RuntimeException {

    private Integer code;
    private String message;

    public LongmarchException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LongmarchException(String message) {
        this(Result.RESPOND_FAIL_CODE, message);
    }

}
