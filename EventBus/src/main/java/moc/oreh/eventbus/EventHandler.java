package moc.oreh.eventbus;

import java.lang.reflect.Method;

/**
 * Created by hero on 17-4-2.
 */
public class EventHandler{
    private final Object handler; // handle xxEvent
    private final Method method;  // which method, like method.invoke(handler, event)

    public EventHandler(Object handler, Method method) {
        this.handler = handler;
        this.method = method;
    }

    public Object getHandler() {
        return handler;
    }

    public Method getMethod() {
        return method;
    }
}
