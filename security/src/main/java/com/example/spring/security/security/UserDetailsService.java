package com.example.spring.security.security;

import org.springframework.stereotype.Component;

/**
 * @author carl
 */
@Component
public class UserDetailsService {

    public UserDetails loadUserByMobile(String countryCode, String mobile) {
        System.out.println("load user by mobile");
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(mobile);
        return userDetails;
    }

    public UserDetails loadUserByEmail(String email) {
        System.out.println("load user by email");
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(email);
        return userDetails;
    }
}
