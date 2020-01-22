package top.longmarch.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

public abstract class AspectHandler {

    public Object doAspectHandler(
            ProceedingJoinPoint pjp, Method method)
            throws Throwable {
        return factoryMethod().doHandlerAspect(pjp, method);
    }

    protected abstract AspectApi factoryMethod();

}
