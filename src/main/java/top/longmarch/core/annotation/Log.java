package top.longmarch.core.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "";

    LogType type() default LogType.OPERATE;
    
    enum LogType {
        OPERATE, //操作日志
        LOGIN //登陆日志
    }
    
}
