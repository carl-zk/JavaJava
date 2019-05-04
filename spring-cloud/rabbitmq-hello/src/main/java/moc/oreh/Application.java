package moc.oreh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * docker run -it --hostname rabbit-server --name my-rabbit -v /opt/rabbitmq/var/lib/rabbitmq:/var/lib/rabbitmq -p 5672:5672 -p 15671:15671 -p 15672:15672 rabbitmq:3-management
 *
 * @author carl
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
