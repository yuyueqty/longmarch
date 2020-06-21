package top.longmarch.core.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import top.longmarch.core.common.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求的 JSON 参数在请求体内的参数校验
     *
     * @param e 异常信息
     * @return 返回数据
     * @throws JsonProcessingException jackson 的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidException(MethodArgumentNotValidException e)
            throws JsonProcessingException {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuffer sb = new StringBuffer();
        allErrors.forEach(o -> sb.append(o.getDefaultMessage()).append("; "));
        return Result.fail(sb.toString());
    }

    /**
     * 请求的 URL 参数检验
     *
     * @param e 异常信息
     * @return 返回提示信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result ConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> allErrors = e.getConstraintViolations();
        StringBuffer sb = new StringBuffer();
        allErrors.forEach(o -> sb.append(o.getMessage()).append("; "));
        return Result.fail(sb.toString());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result UnauthorizedException(UnauthorizedException e) {
        logger.error(e.getMessage());
        return Result.fail(401, "权限不足");
    }

    @ExceptionHandler(LongmarchShiroException.class)
    public Result LongmarchShiroException(LongmarchShiroException e) {
        logger.error(e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(LongmarchException.class)
    public Result LongmarchException(LongmarchException e) {
        logger.error(e.getMessage());
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result Exception(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

}
