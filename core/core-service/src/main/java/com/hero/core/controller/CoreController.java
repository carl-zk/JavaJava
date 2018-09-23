package com.hero.core.controller;

import com.hero.common.Result;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carl
 */
@RestController
@RequestMapping("/api")
public class CoreController {
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/hello")
    public Result hello() {
        InstanceInfo.InstanceStatus instanceRemoteStatus = eurekaClient.getInstanceRemoteStatus();
        System.out.println(instanceRemoteStatus);
        return Result.success("hello");
    }

    @GetMapping("/info/{id}")
    public Result getInfo(@PathVariable("id") int id) {
        return Result.success(id);
    }
}
