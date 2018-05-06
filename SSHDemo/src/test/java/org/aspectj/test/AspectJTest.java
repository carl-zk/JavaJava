package org.aspectj.test;

import org.BaseTest;
import org.ehcache.test.User;
import org.ehcache.test.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hero on 16-5-21.
 */
public class AspectJTest extends BaseTest {

    public void test() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:META-INF/spring/application-context.xml");
        Hello hello = ctx.getBean(Hello.class);
        hello.foo();
        hello.addUser("孙悟空", 12);

        World world = ctx.getBean(World.class);
        world.bar();

        UserService userService = new UserService();
        User user = userService.getUser("1", 1);
        User user1 = userService.getUser("1", 1);
        System.out.println(user==user1);
    }
}
