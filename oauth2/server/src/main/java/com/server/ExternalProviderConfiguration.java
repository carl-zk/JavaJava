package com.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author carl
 */
@Configuration
public class ExternalProviderConfiguration {
    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext oAuth2ClientContext;

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

    @Bean("ssoFilter")
    public Filter ssoFilter() {
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

    private Filter ssoFilter(ExternalProviderConfiguration.ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getResource().getClientId());
        tokenServices.setRestTemplate(template);
        // if github, then assume it has 'spring-projects' authority
        // TODO 2019-03-24 carl: start if ... else ...
        tokenServices.setAuthoritiesExtractor(githubAuthoritiesExtractor);
        // TODO 2019-03-24 carl: end
        filter.setTokenServices(tokenServices);
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/?error=true"));
        return filter;
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
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            //throw new BadCredentialsException("Not in Spring Projects organization");
        };
    }

    @Bean("githubOauth2RestTemplate")
    public OAuth2RestTemplate githubOauth2RestTemplate(@Qualifier("github") ClientResources github, @Qualifier("oauth2ClientContext") OAuth2ClientContext context) {
        return new OAuth2RestTemplate(github.getClient(), context);
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
