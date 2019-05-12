package com.starter.cloudstream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author carl
 */
public interface CustomBindings {
    @Input
    SubscribableChannel customInputA();

    @Output
    MessageChannel customOutputA();
}
