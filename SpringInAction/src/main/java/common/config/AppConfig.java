package common.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


/**
 * Created by hero on 13/04/2018.
 */
@Configuration
@EnableConfigurationProperties
@EnableAspectJAutoProxy
@EnableWebMvc
@ComponentScan(basePackages = {"common", "controller"})
public class AppConfig {
    @Autowired
    Environment env;

    @Bean(destroyMethod = "close")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        dataSource.setInitialSize(env.getProperty("database.minSize", Integer.class));
        dataSource.setMaxTotal(env.getProperty("database.maxSize", Integer.class));
        return dataSource;
    }
}
