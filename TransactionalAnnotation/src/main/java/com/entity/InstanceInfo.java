package com.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by hero on 17-2-28.
 */
public class InstanceInfo implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("------>bean: " + beanName + ", " + bean.hashCode());
        return bean;
    }
}
