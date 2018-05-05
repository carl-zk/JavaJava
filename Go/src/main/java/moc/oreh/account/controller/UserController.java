package moc.oreh.account.controller;

import moc.oreh.account.entity.User;
import moc.oreh.account.repository.UserRepository;
import moc.oreh.account.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import moc.oreh.common.web.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    MyService myService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/index")
    public ModelAndView login() {
        return new ModelAndView("index");
    }

    @RequestMapping("/error")
    public ModelAndView er() {
        ModelAndView error = new ModelAndView("error");
        error.addObject("msg", "错误信息");
        return error;
    }

    @GetMapping("/{userId}")
    public Result getUser(@PathVariable long userId) {
        User user = userRepository.get(userId);
        return Result.success(user);
    }

    @PostMapping("/add")
    public Result edit() {
        User user = new User("积分多少", 22);
        userRepository.save(user);
        return Result.success(user);
    }
}
