package com.hero.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by hero on 16-5-21.
 */
public class ApplicationListener implements ServletContextListener {
    Logger _logger = LoggerFactory.getLogger(this.getClass());

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        _logger.info("====================application start=======================");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        _logger.info("====================application stop=======================");
    }
}
