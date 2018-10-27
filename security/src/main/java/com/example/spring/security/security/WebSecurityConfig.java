package com.example.spring.security.security;

import com.example.spring.security.security.handler.FailedLoginHandler;
import com.example.spring.security.security.handler.SuccessLoginHandler;
import com.example.spring.security.security.provider.EmailAuthenticationProviderImpl;
import com.example.spring.security.security.provider.MobileAuthenticationProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * @author carl
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MobileAuthenticationProviderImpl mobileAuthenticationProvider;
    @Autowired
    private EmailAuthenticationProviderImpl emailAuthenticationProvider;
    @Autowired
    private SuccessLoginHandler successLoginHandler;
    @Autowired
    private FailedLoginHandler failedLoginHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(mobileAuthenticationProvider)
                .authenticationProvider(emailAuthenticationProvider)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .expressionHandler(webSecurityExpressionHandler())
                .antMatchers("/**/open/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**/login", "/**/logout").permitAll()
                .antMatchers("/**/fe/**/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(loginFilter(), AbstractPreAuthenticatedProcessingFilter.class)
        // TODO
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter filter = new LoginFilter("/**/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(successLoginHandler);
        filter.setAuthenticationFailureHandler(failedLoginHandler);
        return filter;
    }


    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        StringBuilder sb = new StringBuilder();
        sb.append("ROLE_ADMIN > ROLE_STAFF");
        sb.append(" and ");
        sb.append("ROLE_STAFF > ROLE_MEMBER");
        sb.append(" and ");
        sb.append("ROLE_MEMBER > ROLE_GO");
        sb.append(" and ");
        sb.append("ROLE_GO > ROLE_BASIC");
        sb.append(" and ");
        sb.append("ROLE_BASIC > ROLE_GUEST");
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(sb.toString());
        return roleHierarchy;
    }
}
