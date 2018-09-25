package com.hero.web.controller;

import com.hero.web.support.Result;
import com.hero.web.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class UserController {

  @GetMapping("/v1/users")
  public Result listUsers() {
    UserVo userVo = new UserVo();
    userVo.setName("小红");
    userVo.setLastLoginAt(LocalDateTime.now());
    return Result.success(userVo);
  }
}
