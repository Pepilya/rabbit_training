package com.advanced.message.training.producer.infrastructure;

import com.advanced.message.training.producer.application.service.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

    private final static String ROUTING_KEY_HEADER = "customRoutingKey";

    private final Processor processor;

    public void publishEvent(Order order) {
        processor.output().send(
                MessageBuilder
                        .withPayload(order)
                        .setHeader(ROUTING_KEY_HEADER, "routing-queue_1")
                        .build());

        processor.output().send(
                MessageBuilder
                        .withPayload(order)
                        .setHeader(ROUTING_KEY_HEADER,  "routing-queue_2")
                        .build());

    }

}
