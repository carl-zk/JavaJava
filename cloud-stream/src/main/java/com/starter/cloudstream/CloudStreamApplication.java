package com.starter.cloudstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author carl
 */
@SpringBootApplication
@EnableBinding({Sink.class, CustomBindings.class})
public class CloudStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void handle(Person person) {
        System.out.println(String.format("MQ Received: %s\n", person));
        throw new RuntimeException();
    }

    @StreamListener("customInputA")
    public void handle(String msg) {
        System.out.println("Received: " + msg);
    }

}
