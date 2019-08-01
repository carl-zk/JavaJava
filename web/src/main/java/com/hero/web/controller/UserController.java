package com.hero.web.controller;

import com.hero.web.constant.Result;
import com.hero.web.constant.ServiceException;
import com.hero.web.vo.UserVO;
import org.springframework.context.i18n.LocaleContextHolder;
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
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author carl
 */
@Validated
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/v1/users")
    public Result listUsers(@RequestParam @Size(max = 1) List<String> uuids) {
        System.out.println(LocaleContextHolder.getLocale());
        System.out.println(uuids);

        UserVO userVo = new UserVO();
        userVo.setName("小红");
        userVo.setLastLoginAt(LocalDateTime.now());
        return Result.success(userVo);
    }

    @GetMapping("/v1/user/{uuid}")
    public Result getUser(@PathVariable String uuid) {
        throw new ServiceException(600, "no such user");
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
