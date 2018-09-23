package com.hero.user.controller;

import com.hero.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private CoreService coreService;

    @Value("${kitty}")
    private String kitty;

    @GetMapping("/hello")
    public Result getFromCore() {
        System.out.println("begin");
        Result result = coreService.hello();
        System.out.println("success");
        return result;
    }

    @GetMapping("/info")
    public Result getInfo() {
        return coreService.getInfo(1);
    }

    @GetMapping("/a")
    public Result apollo() {
        return Result.success(kitty);
    }
}
