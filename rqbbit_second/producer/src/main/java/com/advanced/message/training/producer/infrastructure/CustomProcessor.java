package com.advanced.message.training.producer.infrastructure;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomProcessor {

    String QUEUE1 = "output-queue1";

    @Output(QUEUE1)
    MessageChannel output1();

    String QUEUE2 = "output-queue2";

    @Output(QUEUE2)
    MessageChannel output2();

}
