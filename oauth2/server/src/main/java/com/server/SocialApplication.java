package com.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.boot.SpringApplication.run;

@EnableAuthorizationServer
@EnableOAuth2Client
@SpringBootApplication
@Controller
public class SocialApplication extends WebSecurityConfigurerAdapter {

    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext oAuth2ClientContext;

    public static void main(String[] args) {
        run(SocialApplication.class, args);
    }

    @RequestMapping({"/user", "/me"})
    @ResponseBody
    public Principal getUser(Principal principal) {
        System.out.println("server end user/me ");
        System.out.println(oAuth2ClientContext.getAccessToken());
        return principal;
    }

    @Order(-1)
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration
            extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/me")
                    .authorizeRequests().anyRequest().authenticated();
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean("githubOauth2RestTemplate")
    public OAuth2RestTemplate githubOauth2RestTemplate(@Qualifier("github") ClientResources github, @Qualifier("oauth2ClientContext") OAuth2ClientContext context) {
        return new OAuth2RestTemplate(github.getClient(), context);
    }

    @Bean("githubAuthoritiesExtractor")
    public AuthoritiesExtractor githubAuthoritiesExtractor(OAuth2RestOperations template) {
        return map -> {
            String url = (String) map.get("organizations_url");
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> orgs = template.getForObject(url, List.class);
            if (orgs.stream()
                    .anyMatch(org -> "spring-projects".equals(org.get("login")))) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            }
            //return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            throw new BadCredentialsException("Not in Spring Projects origanization");
        };
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();

        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));

        filter.setFilters(filters);
        return filter;
    }

    @Qualifier("githubAuthoritiesExtractor")
    @Autowired
    AuthoritiesExtractor githubAuthoritiesExtractor;

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getResource().getClientId());
        tokenServices.setRestTemplate(template);
        // if github, then assume it has 'spring-projects' authority
        tokenServices.setAuthoritiesExtractor(githubAuthoritiesExtractor);
        filter.setTokenServices(tokenServices);
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/?error=true"));
        return filter;
    }

    @Bean("facebook")
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean("github")
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> oAuth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }
}
