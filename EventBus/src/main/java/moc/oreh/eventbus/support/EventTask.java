package moc.oreh.eventbus.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by hero on 17-4-3.
 */
public class EventTask implements Runnable {
    private Log log = LogFactory.getLog(EventTask.class);

    private final Subscriber subscriber;
    private final EventTarget eventTarget;

    public void run() {
        try {
            subscriber.onEvent(eventTarget);
        } catch (Exception e) {
            log.error("[EventTask executed failed] ", e);
        }
    }

    public EventTask(Subscriber subscriber, EventTarget eventTarget) {
        this.subscriber = subscriber;
        this.eventTarget = eventTarget;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public EventTarget getEventTarget() {
        return eventTarget;
    }
}
