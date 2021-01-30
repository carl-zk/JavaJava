package com.carl.web.common;


import com.carl.web.support.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author carl
 */
@Component
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    ResponseResultInterceptor responseResultInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseResultInterceptor);
    }
}
