package com.controller;

import com.dao.UserRepository;
import com.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * Created by hero on 17-2-27.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private SessionFactory sessionFactory;


    @RequestMapping("/save")
    @ResponseBody
    public String save(String name, int age) {
        User user = new User(name, age);
        userRepository.save(user);
        return "successed";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(timeout = 10)
    public String get(String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        User user = userRepository.getUser(name);
        return user.toString();
    }

    @RequestMapping("/sf")
    @ResponseBody
  //  @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 1)
    public String isNull() throws InterruptedException {
        //TimeUnit.SECONDS.sleep(2);
        userRepository.test();
        return "hh";
    }
}
