package com.wework.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author carl
 */
@Configuration
public class BeanConfig {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.setReadTimeout(1000).setConnectTimeout(1000).build();
  }
}
