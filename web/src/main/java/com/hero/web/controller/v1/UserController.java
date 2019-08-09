package com.hero.web.controller.v1;

import com.hero.web.common.Result;
import com.hero.web.domain.vo.UserVO;
import com.hero.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author carl
 */
@Validated
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Result listUsers(@RequestParam @Size(max = 10) List<String> uuids,
                            @RequestParam(defaultValue = "0") Integer page,
                            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(userService.getUsersByUuids(uuids));
    }

    @GetMapping("/user/{id}")
    public Result getUser(@PathVariable Long id) {
        return Result.success(userService.getUser(id));
    }

    @PostMapping("/user")
    public Result createUser(@RequestBody @Validated(UserVO.WhenCreate.class) UserVO userVO) {
        return Result.success(userService.register(userVO));
    }

    @PutMapping("/user")
    public Result updateUser(@RequestBody @Validated(UserVO.WhenUpdate.class) UserVO userVO) {
        return Result.success(userVO);
    }
}
