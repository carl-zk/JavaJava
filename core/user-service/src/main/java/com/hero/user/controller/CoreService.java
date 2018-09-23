package com.hero.user.controller;

import com.hero.common.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author carl
 */
@FeignClient(value = "core-service", path = "/api", fallback = FallbackCoreService.class)
public interface CoreService {
    /**
     * hello
     *
     * @return
     */
    @GetMapping("hello")
    Result hello();

    /**
     * info
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    Result getInfo(@PathVariable("id") int id);
}
