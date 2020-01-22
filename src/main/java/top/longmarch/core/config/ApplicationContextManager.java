package top.longmarch.core.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextManager implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ApplicationContextManager.applicationContext == null) {
            ApplicationContextManager.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return ApplicationContextManager.getApplicationContext().getBean(requiredType);
    }
    
    public static Object getBean(String name) throws BeansException {
        return ApplicationContextManager.getApplicationContext().getBean(name);
    }
    
    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return ApplicationContextManager.getApplicationContext().getBean(name, requiredType);
    }

}
