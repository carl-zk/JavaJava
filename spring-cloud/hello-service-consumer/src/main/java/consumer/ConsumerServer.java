package consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author carl
 */
@EnableFeignClients({"api"})
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerServer {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServer.class, args);
    }
}
