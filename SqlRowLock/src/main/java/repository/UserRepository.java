package repository;

import entity.User;

/**
 * Created by carl on 5/20/17.
 */
public interface UserRepository {
    User get(int id);
    int save(User user);
}
