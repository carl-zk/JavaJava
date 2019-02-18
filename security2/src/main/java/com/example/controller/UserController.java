package com.example.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author carl
 */
@RestController
public class UserController {
    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/hello")
    public void login() {
        System.out.println("hello world");
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
    }

    @Secured("ROLE_USER")
    @GetMapping("/admin/hello")
    public void hello() {
        redissonClient.getMap("map").put("a", "abcd");
        redissonClient.getMapCache("ca").put("b", "bb", 10, TimeUnit.DAYS);
        System.out.println("hello admin");
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
    }
}
