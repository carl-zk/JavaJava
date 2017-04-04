package common;

import moc.oreh.eventbus.EventBus;
import moc.oreh.eventbus.spring.SpringEventBus;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by hero on 17-4-2.
 */
public class Register implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public static EventBus getEventBus() {
        return context.getBean(SpringEventBus.class);
    }
}
