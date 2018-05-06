package org.hibernate.controller;

import org.hibernate.test.User;
import org.hibernate.test.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by hero on 16-5-22.
 */
@Controller
@RequestMapping(value = "/identity")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "user/save")
    public String saveUser(String name){
        User user = new User(name);
        userRepository.save(user);
        return "已保存: "+ user.getName();
    }

    @RequestMapping(value = "/user/get")
    @ResponseBody
    public String getUser(int id, HttpServletRequest request, HttpServletResponse response){
        User user = userRepository.get(id);
        return user.toString();
    }
}
