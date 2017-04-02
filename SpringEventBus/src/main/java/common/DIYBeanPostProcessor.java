package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by hero on 17-4-2.
 */
public class DIYBeanPostProcessor implements BeanPostProcessor {
    Logger logger = LogManager.getLogger(DIYBeanPostProcessor.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("psotPro: " + bean.getClass().getName());
        return bean;
    }
}
