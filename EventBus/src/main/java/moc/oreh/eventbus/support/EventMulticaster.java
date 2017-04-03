package moc.oreh.eventbus.support;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by hero on 17-4-3.
 */
public class EventMulticaster {
    private Map<Class, LinkedList<Subscriber>> retrieverCache = new ConcurrentHashMap<Class, LinkedList<Subscriber>>(64);
    private ThreadPoolExecutor syncTaskExecutor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));
    private ThreadPoolExecutor asyncTaskExecutor;

    public EventMulticaster() {
        // SubscribeMode.ASYNC
        asyncTaskExecutor = new ThreadPoolExecutor(16, 32, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));
    }

    public EventMulticaster(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        // SubscribeMode.ASYNC
        asyncTaskExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public void multicastEvent(EventTarget eventTarget) {
        Class eventType = eventTarget.getEvent().getClass();
        LinkedList<Subscriber> subscribers = retrieverCache.get(eventType);
        invokeSubscribers(eventTarget, subscribers);
    }

    private void invokeSubscribers(EventTarget eventTarget, LinkedList<Subscriber> subscribers) {
        for (Subscriber subscriber : subscribers) {
            switch (subscriber.getMode()) {
                case ASYNC:
                    asyncTaskExecutor.execute(new EventTask(subscriber, eventTarget));
                    break;
                case SYNC:
                    syncTaskExecutor.execute(new EventTask(subscriber, eventTarget));
                    break;
                case FOLLOW:
                    new EventTask(subscriber, eventTarget).run();
                    break;
            }
        }
    }

    public LinkedList<Subscriber> getSubscribers(Class eventType) {
        return retrieverCache.get(eventType);
    }

    public void setSubscribers(Class eventType, LinkedList<Subscriber> subscribers) {
        retrieverCache.put(eventType, subscribers);
    }

    public void destroy(){
        retrieverCache = null;
        syncTaskExecutor.shutdown();
        asyncTaskExecutor.shutdown();
    }

    public void addSubscriber(Subscriber subscriber) {

    }

    public void removeSubscriber(Subscriber subscriber) {

    }

    public void cleanSubscribers() {

    }
}
