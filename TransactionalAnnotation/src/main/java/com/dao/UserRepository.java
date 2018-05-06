package com.dao;

import com.entity.User;

/**
 * Created by hero on 17-2-27.
 */
public interface UserRepository {
    User getUser(String name);
    void save(User user);
    void test();
}
