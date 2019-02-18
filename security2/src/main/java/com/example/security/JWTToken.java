package com.example.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author carl
 */
public class JWTToken extends AbstractAuthenticationToken {
    private String jwt;

    public JWTToken(String jwt) {
        this(jwt, null);
        super.setAuthenticated(false);
    }

    public JWTToken(String jwt, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.jwt = jwt;
        super.setAuthenticated(true);
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (true == authenticated) {
            throw new IllegalArgumentException();
        }
        super.setAuthenticated(false);
    }
}
