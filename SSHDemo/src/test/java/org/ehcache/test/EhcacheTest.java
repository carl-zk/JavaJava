package org.ehcache.test;

import org.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ehcache.test.*;

import java.util.Collection;

/**
 * Created by hero on 16-5-22.
 */
public class EhcacheTest extends BaseTest{
    private Logger _logger = LoggerFactory.getLogger(this.getClass());
    public void test() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:META-INF/spring/application-context.xml");
        UserService userService = ctx.getBean(UserService.class);
        User u1=userService.getUser("1",1);
        User u2=userService.getUser("1",1);
        System.out.println(u1==u2);

        CacheManager cacheManager = ctx.getBean(CacheManager.class);
        System.out.println("cache name: "+cacheManager.getCacheNames());
    }
}
