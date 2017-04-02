package moc.oreh.eventbus;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;

/**
 * should config paras in xml before use
 * remember destroy on destroy-method
 *config like this,
 * <bean class="moc.oreh.eventbus.EventBus" destroy-method="destroy">
 *   <constructor-arg name="corePoolSize" value="8"/>
 *   <constructor-arg name="maximumPoolSize" value="32"/>
 *   <constructor-arg name="keepAliveTime" value="300"/>
 * </bean>
 *
 * @author hero on 17-4-2.
 */
public class EventBus implements BeanPostProcessor, ApplicationContextAware {
    private static ApplicationContext context;
    private Map<Class, LinkedList<EventHandler>> bus;
    private ThreadPoolExecutor taskExecutor;

    public EventBus(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        bus = new ConcurrentHashMap<Class, LinkedList<EventHandler>>(64);
        taskExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * publish an event
     *
     * @param event  an Event Object, can any customized class object
     * @param source the event publisher, which eventBus in to publish this event
     */
    public void publish(final Object event, final Object source) {
        Class clazz = event.getClass();
        final LinkedList<EventHandler> handlers = bus.get(clazz);
        if (handlers == null)
            throw new IllegalArgumentException("no handler subscribe this event: " + clazz.getName());
        taskExecutor.execute(new Runnable() {
            public void run() {
                for (EventHandler handler : handlers) {
                    try {
                        handler.getMethod().invoke(handler.getHandler(), event);
                    } catch (Exception e) {
                        throw new RuntimeException("event handler method invoke exception", e);
                    }
                }
            }
        });
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class[] event = method.getParameterTypes();
                if (event == null || event.length != 1)
                    throw new IllegalArgumentException("event must only single: " + method.getName() + " in " + clazz.getName());
                LinkedList<EventHandler> handlers = bus.get(event[0]);
                if (handlers == null) {
                    handlers = new LinkedList<EventHandler>();
                    bus.put(event[0], handlers);
                }
                handlers.addLast(new EventHandler(bean, method));
            }
        }
        return bean;
    }

    public void destroy() {
        taskExecutor.shutdown();
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        context = context;
    }
}
