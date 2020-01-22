package top.longmarch.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.longmarch.core.annotation.Log;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class AspectProcessor {

    @Autowired
    private LogService logService;

    @Around("within(top.longmarch..*) && @annotation(log)")
    public Object recordLogHandler(ProceedingJoinPoint pjp, Log log) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Method method = getMethod(pjp);
        AspectHandler handler = new RecordLogHandler(request, logService);
        return handler.doAspectHandler(pjp, method);
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            method = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

}
