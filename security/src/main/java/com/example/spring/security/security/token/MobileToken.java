package com.example.spring.security.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author carl
 */
public class MobileToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -272877498964899506L;

    private final String countryCode;
    private final String mobile;
    private final String verifyCode;

    /**
     *
     * @param countryCode
     * @param mobile
     * @param verifyCode
     */
    public MobileToken(String countryCode, String mobile, String verifyCode) {
        super(null);
        this.countryCode = countryCode;
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        setAuthenticated(false);
    }

    /**
     *
     * @param authorities
     * @param countryCode
     * @param mobile
     * @param verifyCode
     */
    public MobileToken(Collection<? extends GrantedAuthority> authorities, String countryCode, Object mobile, Object verifyCode) {
        super(authorities);
        this.countryCode = countryCode;
        this.mobile = (String) mobile;
        this.verifyCode = (String) verifyCode;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return verifyCode;
    }

    @Override
    public Object getPrincipal() {
        return mobile;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
