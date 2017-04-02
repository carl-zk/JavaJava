package commons;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Assume;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hero on 17-3-26.
 * log4j2 test
 */
public class LogTest {
    @Test
    public void test() {
        Logger logger = LogManager.getLogger(LogTest.class);
        logger.debug("debug level");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");

        int t = 0;
        try {
            t = 1 / t;
        } catch (Exception ex) {
            logger.error("failed: ", ex);
        }
    }
}
