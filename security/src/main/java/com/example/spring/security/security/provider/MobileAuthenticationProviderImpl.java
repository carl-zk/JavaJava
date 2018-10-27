package com.example.spring.security.security.provider;

import com.example.spring.security.security.UserDetailsService;
import com.example.spring.security.security.token.MobileToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author carl
 */
@Component
public class MobileAuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileToken mobileToken = (MobileToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByMobile(mobileToken.getCountryCode(), (String) mobileToken.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("failed...");
        }

        MobileToken success = new MobileToken(AuthorityUtils.createAuthorityList("BASIC"), mobileToken.getCountryCode(), mobileToken.getPrincipal(), mobileToken.getCredentials());
        success.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(success);
        System.out.println("success with mobile");
        return success;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileToken.class.isAssignableFrom(authentication);
    }
}
