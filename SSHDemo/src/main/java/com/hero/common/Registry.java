package com.hero.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Created by hero on 16-5-21.
 */
public class Registry implements ApplicationContextAware {
    private static ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static Object getBean(String beanName){
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
