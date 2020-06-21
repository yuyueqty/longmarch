package top.longmarch.core.exception;

import lombok.Data;
import org.apache.shiro.ShiroException;

@Data
public class LongmarchShiroException extends ShiroException {

    private Integer code;
    private String message;

    public LongmarchShiroException(Integer code) {
        this.code = code;
    }

    public LongmarchShiroException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
