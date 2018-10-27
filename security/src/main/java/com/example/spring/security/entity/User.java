package com.example.spring.security.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carl
 */
@Getter
@Setter
public class User {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
