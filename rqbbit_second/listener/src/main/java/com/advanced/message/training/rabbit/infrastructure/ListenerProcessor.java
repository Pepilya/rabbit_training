package com.advanced.message.training.rabbit.infrastructure;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ListenerProcessor {

    String INPUT = "orders";

    @Input(INPUT)
    MessageChannel listenOrder();
}
