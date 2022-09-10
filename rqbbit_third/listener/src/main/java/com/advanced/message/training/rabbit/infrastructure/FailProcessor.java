package com.advanced.message.training.rabbit.infrastructure;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FailProcessor {
    String OUTPUT = "fail";

    @Output(OUTPUT)
    MessageChannel failOrder();
}
