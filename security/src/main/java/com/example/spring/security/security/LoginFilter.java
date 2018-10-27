package com.example.spring.security.security;

import com.alibaba.fastjson.JSONObject;
import com.example.spring.security.security.token.EmailToken;
import com.example.spring.security.security.token.MobileToken;
import com.example.spring.security.vo.LoginVo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author carl
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    protected LoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginVo loginVo = extractLoginVo(request);
        switch (loginVo.getLoginType()) {
            case MOBILE:
                System.out.println("login with mobile ...");
                MobileToken mobileToken = new MobileToken(loginVo.getCountryCode(), loginVo.getMobile(), loginVo.getVerifyCode());
                mobileToken.setDetails(authenticationDetailsSource.buildDetails(request));
                return getAuthenticationManager().authenticate(mobileToken);
            case EMAIL:
                System.out.println("login with email ...");
                EmailToken emailToken = new EmailToken(loginVo.getEmail(), loginVo.getPassword());
                emailToken.setDetails(authenticationDetailsSource.buildDetails(request));
                return getAuthenticationManager().authenticate(emailToken);
            default:
        }
        throw new BadCredentialsException("用户名/密码错误");
    }

    private LoginVo extractLoginVo(HttpServletRequest request) {
        LoginVo loginVo = new LoginVo();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String ln;
            while ((ln = reader.readLine()) != null) {
                sb.append(ln);
            }
            loginVo = JSONObject.parseObject(sb.toString(), LoginVo.class);
            return loginVo;
        } catch (Exception e) {
            return loginVo;
        }
    }
}
