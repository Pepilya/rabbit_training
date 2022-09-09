package com.advanced.message.training.rabbit.infrastructure;

import com.advanced.message.training.rabbit.application.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FailProducer {

    private final FailProcessor processor;

    public void sendFailedEvent(Error error) {
        processor.failOrder().send(MessageBuilder.withPayload(error).build());
    }
}
