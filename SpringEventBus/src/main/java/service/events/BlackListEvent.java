package service.events;

import org.springframework.context.ApplicationEvent;

/**
 * Created by hero on 17-4-2.
 */
public class BlackListEvent extends ApplicationEvent {
    private static final long serialVersionUID = 2761259651923710870L;
    private final String address;
    private final String text;

    public BlackListEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    @Override
    public String toString() {
        return "BlackListEvent{" +
                "address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
