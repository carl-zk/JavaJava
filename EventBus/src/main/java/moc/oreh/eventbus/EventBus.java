package moc.oreh.eventbus;

import moc.oreh.eventbus.annotation.Subscribe;
import moc.oreh.eventbus.support.Subscriber;
import moc.oreh.eventbus.support.EventMulticaster;
import moc.oreh.eventbus.support.EventTarget;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * @author hero on 17-4-2.
 */
public class EventBus implements BeanPostProcessor, ApplicationContextAware {
    private static ApplicationContext context;
    private EventMulticaster eventMulticaster;

    public EventBus() {
        eventMulticaster = new EventMulticaster();
    }

    public EventBus(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        eventMulticaster = new EventMulticaster(corePoolSize, maximumPoolSize, keepAliveTime);
    }

    public void publish(Object event) {
        publish(event, null);
    }

    /**
     * publish an event
     *
     * @param event  an Event Object, can any customized class object
     * @param source the event publisher, which eventBus in to publish this event
     */
    public void publish(Object event, Object source) {
        EventTarget eventTarget = new EventTarget(event, source);
        Class eventType = event.getClass();
        LinkedList<Subscriber> subscribers = eventMulticaster.getSubscribers(eventType);
        if (subscribers == null)
            throw new IllegalArgumentException("no subscribers on this event: " + eventType.getName());
        eventMulticaster.multicastEvent(eventTarget);
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Annotation annotation = method.getAnnotation(Subscribe.class);
                Subscribe subscribe = (Subscribe) annotation;
                Class[] event = method.getParameterTypes();
                if (event == null || event.length != 1)
                    throw new IllegalArgumentException("event object must one and only : " + method.getName() + " in " + clazz.getName());
                Class eventType = event[0];
                LinkedList<Subscriber> subscribers = eventMulticaster.getSubscribers(eventType);
                if (subscribers == null) {
                    subscribers = new LinkedList<Subscriber>();
                    eventMulticaster.setSubscribers(eventType, subscribers);
                }
                subscribers.addLast(new Subscriber(bean, method, eventType, subscribe.mode()));
            }
        }
        return bean;
    }

    public void destroy() {
        eventMulticaster.destroy();
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
