package com.starter.cloudstream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@DirtiesContext
public class CloudStreamApplicationTests {

    @Autowired
    private Sink sink;

    @Autowired
    private SubscribableChannel customInputA;
    @Autowired
    private MessageChannel customOutputA;

    @Test
    public void contextLoads() {
        assert sink.input() != null;
    }

    @Test
    public void customInputATest() {
        customInputA.subscribe((msg -> System.out.println(msg.getPayload())));
    }

    @Test
    public void customOutputATest() {
        //customInputA.send(MessageBuilder.withPayload("hello world").build());
        customOutputA.send(MessageBuilder.withPayload("hello world").build());
    }
}
