package com.example.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedList;
import java.util.List;

/**
 * @carl
 */
public class JWTAuthenProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JWTToken token = (JWTToken) authentication;
        System.out.println("jwt auth provider: " + token.getJwt());
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        JWTToken authToken = new JWTToken(token.getJwt(), authorities);
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTToken.class.equals(authentication);
    }
}
