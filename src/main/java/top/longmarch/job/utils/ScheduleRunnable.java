package top.longmarch.job.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.ReflectionUtils;
import top.longmarch.core.config.ApplicationContextManager;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 */
public class ScheduleRunnable implements Runnable {

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params)
            throws NoSuchMethodException, SecurityException {
        this.target = ApplicationContextManager.getBean(beanName);
        this.params = params;

        if (StrUtil.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StrUtil.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            throw new RuntimeException("执行定时任务失败", e);
        }
    }

}
