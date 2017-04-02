package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by hero on 17-4-2.
 */
public class Lifecycle implements ServletContextListener {
    Logger logger = LogManager.getLogger(Lifecycle.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("--------------system start-----------------");
        logger.info(sce.getServletContext().getRealPath("/"));
    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("--------------system shutdown-----------------");
    }
}
