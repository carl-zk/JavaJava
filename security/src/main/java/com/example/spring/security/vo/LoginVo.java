package com.example.spring.security.vo;

import com.example.spring.security.enums.LoginType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author carl
 */
@Getter
@Setter
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1721029549088632650L;

    private LoginType loginType;
    private String countryCode;
    private String mobile;
    private String verifyCode;
    private String email;
    private String password;
}
