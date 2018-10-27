package com.example.spring.security.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author carl
 */
public class EmailToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -1579803779029935130L;

    private final String email;
    private final String password;

    /**
     *
     * @param email
     * @param password
     */
    public EmailToken(String email, String password) {
        super(null);
        this.email = email;
        this.password = password;
        setAuthenticated(false);
    }

    /**
     *
     * @param authorities
     * @param email
     * @param password
     */
    public EmailToken(Collection<? extends GrantedAuthority> authorities, Object email, Object password) {
        super(authorities);
        this.email = (String) email;
        this.password = (String) password;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return email;
    }
}
