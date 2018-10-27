package com.example.spring.security.controller;

import com.example.spring.security.entity.User;
import com.example.spring.security.security.provider.MobileAuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private MobileAuthenticationProviderImpl mobileAuthenticationProvider;

    @PostMapping("/login")
    public User login(@RequestBody User user) {

        return user;
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/info")
    public String getInfo() {

        return "123";
    }

    @GetMapping("/open/info")
    public String openInfo() {

        return "open";
    }
}
