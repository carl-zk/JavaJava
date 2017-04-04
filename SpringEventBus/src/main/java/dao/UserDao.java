package dao;

import entity.User;

/**
 * Created by hero on 17-4-3.
 */
public interface UserDao {
    void save(User user);
    User get(String userId);
}
