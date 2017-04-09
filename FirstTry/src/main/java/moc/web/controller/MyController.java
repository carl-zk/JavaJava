package moc.web.controller;

import com.sun.deploy.net.HttpResponse;
import moc.identity.User;
import moc.identity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hero on 16-6-1.
 */
@Controller
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{word}", method = RequestMethod.GET)
    @ResponseBody
    public String say(@PathVariable String word) {
        System.out.println(word);
        return word;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(String id,  HttpServletRequest request, HttpServletResponse response) {
        User user = userRepository.get();
        return user;
    }

//    同属性多对象的数据绑定
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@ModelAttribute("user1") User user1,@ModelAttribute("user2") User user2){
        System.out.println(user1.toString());
        return user1.toString() + user2.toString();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getContentType() + ", name=" + file.getOriginalFilename());
        return "/hello world";
    }

    @InitBinder("user1")
    public void initUser1(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user1.");
    }

    @InitBinder("user2")
    public void initUser2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user2.");
    }
}
