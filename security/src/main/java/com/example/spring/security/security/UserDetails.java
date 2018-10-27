package com.example.spring.security.security;

import com.example.spring.security.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * @author carl
 */
public class UserDetails extends User implements org.springframework.security.core.userdetails.UserDetails {
    private static final long serialVersionUID = -8204050943474028995L;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
