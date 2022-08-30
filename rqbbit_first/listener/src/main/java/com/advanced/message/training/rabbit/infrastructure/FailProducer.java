package com.advanced.message.training.rabbit.infrastructure;

import com.advanced.message.training.rabbit.application.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FailProducer {

    private final Processor processor;

    public void sendFailedEvent(Error error) {
        processor.output().send(MessageBuilder.withPayload(error).build());
    }
}
