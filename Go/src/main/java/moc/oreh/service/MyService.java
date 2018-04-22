package moc.oreh.service;

import moc.oreh.entity.User;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    public User getUser() {
        return new User("王美丽", 180);
    }
}
