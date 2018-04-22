package moc.oreh.controller;

import moc.oreh.common.Result;
import moc.oreh.entity.User;
import moc.oreh.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    MyService myService;

    @GetMapping("/login")
    public Result login(HttpServletRequest request, @RequestParam String name) {
        User user = myService.getUser();
        return Result.success(user);
    }
}
