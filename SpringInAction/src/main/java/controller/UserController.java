package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hero on 14/04/2018.
 */
@RestController
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(){
        System.out.println("login");
        throw new RuntimeException("have no privilege");
    }
}
