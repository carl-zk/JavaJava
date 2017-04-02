package service.myeventbus;

import entity.User;

/**
 * Created by hero on 17-4-3.
 */
public class OnlineEvent {
    private User user;

    public OnlineEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
