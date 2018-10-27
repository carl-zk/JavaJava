package com.example.spring.security.security.provider;

import com.example.spring.security.security.UserDetails;
import com.example.spring.security.security.UserDetailsService;
import com.example.spring.security.security.token.EmailToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author carl
 */
@Component
public class EmailAuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailToken emailToken = (EmailToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByEmail((String) emailToken.getPrincipal());

        if (userDetails == null) {
            throw new UsernameNotFoundException("新建");
        }

        EmailToken success = new EmailToken(AuthorityUtils.createAuthorityList("ROLE_ADMIN"), emailToken.getPrincipal(), emailToken.getCredentials());
        success.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(success);
        System.out.println("success with email");
        return success;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmailToken.class.isAssignableFrom(authentication);
    }
}
