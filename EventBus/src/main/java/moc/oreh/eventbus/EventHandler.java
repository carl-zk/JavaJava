package moc.oreh.eventbus;

/**
 * Created by hero on 17-4-2.
 */
public interface EventHandler<E> {
    void handle(E event);
}
