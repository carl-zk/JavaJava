package moc.oreh.eventbus.support;

/**
 * Created by hero on 17-4-3.
 */
public class EventTarget {
    private final Object event;
    private final Object source;

    public EventTarget(Object event) {
        this(event, null);
    }

    public EventTarget(Object event, Object source) {
        if (event == null)
            throw new IllegalArgumentException("event must not be null");
        this.event = event;
        this.source = source;
    }

    public Object getEvent() {
        return event;
    }

    public Object getSource() {
        return source;
    }
}
