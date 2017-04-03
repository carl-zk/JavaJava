package moc.oreh.eventbus.support;

import moc.oreh.eventbus.annotation.SubscribeMode;

import java.lang.reflect.Method;

/**
 * Created by hero on 17-4-3.
 */
public class Subscriber {
    private Object subscriber;
    private Method handle;
    private Class EventType;
    private SubscribeMode mode;

    public Subscriber(Object subscriber, Method handle, Class eventType, SubscribeMode mode) {
        this.subscriber = subscriber;
        this.handle = handle;
        this.EventType = eventType;
        this.mode = mode;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Object subscriber) {
        this.subscriber = subscriber;
    }

    public Method getHandle() {
        return handle;
    }

    public void setHandle(Method handle) {
        this.handle = handle;
    }

    public Class getEventType() {
        return EventType;
    }

    public void setEventType(Class eventType) {
        EventType = eventType;
    }

    public SubscribeMode getMode() {
        return mode;
    }

    public void setMode(SubscribeMode mode) {
        this.mode = mode;
    }

    public void onEvent(EventTarget eventTarget) throws Exception {
        handle.invoke(subscriber, eventTarget.getEvent());
    }
}
