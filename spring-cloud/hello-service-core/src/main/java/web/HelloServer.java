package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author carl
 */
@EnableDiscoveryClient
@SpringBootApplication
public class HelloServer {
    public static void main(String[] args) {
        SpringApplication.run(HelloServer.class, args);
    }
}
