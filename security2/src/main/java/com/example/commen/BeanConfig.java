package com.example.commen;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author carl
 */
@Configuration
public class BeanConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setDatabase(1);

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
