/*
package com.carl.web.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

*/
/**
 * @author carl
 *//*

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).enable(true).select()
                // apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage("com.carl.web")).build()
                .securitySchemes(securitySchemes()).securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("基于Swagger3.0的接口文档").description("api信息列表")
                .contact(new Contact("Carl", "spring boot project", "zxfspace@163.com")).version("1.0").build();

    }

    */
/**
     * 设置授权信息
     *//*

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(HttpAuthenticationScheme.BASIC_AUTH_BUILDER.name("jwt").build());
        securitySchemes.add(HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("basic").build());
        return securitySchemes;
    }

    private List<SecurityReference> securityReferences() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("jwt", authorizationScopes));
        securityReferences.add(new SecurityReference("basic", authorizationScopes));
        return securityReferences;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(securityReferences()).forPaths(PathSelectors.any()).build();
    }
}*/
