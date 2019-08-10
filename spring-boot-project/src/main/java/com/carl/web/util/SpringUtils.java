package com.carl.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author carl
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static <T> T getBean(Class<T> classType) {
        return ctx.getBean(classType);
    }

    public static <T> T getBean(String beanName, Class<T> classType) {
        return ctx.getBean(beanName, classType);
    }

    public static ApplicationContext getContext() {
        return ctx;
    }
}
