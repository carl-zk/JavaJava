package com.hero.web.controller;

import com.hero.web.support.Result;
import com.hero.web.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/v1/users")
    public Result listUsers() {
        UserVO userVo = new UserVO();
        userVo.setName("小红");
        userVo.setLastLoginAt(LocalDateTime.now());
        return Result.success(userVo);
    }

    @PostMapping("/v1/user")
    public Result createUser(@RequestBody @Validated(UserVO.ValidationName.class) UserVO userVO) {
        return Result.success(userVO);
    }

    @PutMapping("/v1/user")
    public Result updateUser(@RequestBody @Validated(UserVO.ValidationId.class) UserVO userVO) {
        return Result.success(userVO);
    }
}
