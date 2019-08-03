package com.hero.web.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

/**
 * @author carl
 */
@Slf4j
@Component
public class AppLifecycle implements ServletContextListener {
    @Value("${spring.timezone}")
    private String timezone;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("set system timezone to : {}", timezone);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));

        log.info("-------------- app started --------------------");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        log.info("--------------- app terminated ---------------------");
    }
}
