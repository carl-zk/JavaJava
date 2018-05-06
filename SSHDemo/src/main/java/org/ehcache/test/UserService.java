package org.ehcache.test;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by hero on 16-5-22.
 */
@Service
public class UserService {
    @Cacheable("users")
    public User getUser(String name, int age){
        return new User(name, age);
    }

    public User getAno(String name, int age){
        return new User(name, age);
    }
}
