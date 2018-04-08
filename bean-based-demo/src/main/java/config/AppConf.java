package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hero
 */
@Configuration
@ComponentScan(value = {"config", "dao", "service"})
@EnableJpaRepositories(basePackages = "dao")
public class AppConf {

}
