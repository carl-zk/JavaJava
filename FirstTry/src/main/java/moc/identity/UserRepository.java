package moc.identity;

import org.springframework.stereotype.Repository;

/**
 * Created by hero on 16-6-1.
 */
@Repository
public class UserRepository {

    public User get() {
        return new User("小明", 12);
    }
}
