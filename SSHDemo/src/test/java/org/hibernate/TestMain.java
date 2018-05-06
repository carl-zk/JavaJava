package org.hibernate;

import org.BaseTest;
import org.hibernate.test.User;
import org.hibernate.test.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hero on 16-5-22.
 */
public class TestMain extends BaseTest{
    public void test() throws Exception {
        ApplicationContext cxt = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
        User user = new User("小红");
        UserRepository userRepository = cxt.getBean("userRepository", UserRepository.class);
        userRepository.save(user);
    }
}
