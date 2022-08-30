package com.advanced.message.training.rabbit.infrastructure;

import com.advanced.message.training.rabbit.application.Error;
import com.advanced.message.training.rabbit.application.Order;
import com.advanced.message.training.rabbit.application.OrderService;
import com.advanced.message.training.rabbit.application.ProductAmountExceededException;
import com.advanced.message.training.rabbit.application.ProductNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Listener {
    private final int MAX_ATTEMPTS_COUNT = 2;

    private final OrderService orderService;
    private final FailProducer failProducer;

    @StreamListener(Processor.INPUT)
    public void processEvent(Message<Order> message) {
        Order order = message.getPayload();
        log.info("Get order with payload == {} \n headers == {} \n", order, message.getHeaders());
        try {
            orderService.process(order);
        } catch (ProductNotAvailableException ex) {
            throw new ImmediateAcknowledgeAmqpException(ex.getMessage());
        } catch (ProductAmountExceededException ex) {
            if (isLastAttempt(message)) {
                failProducer.sendFailedEvent(new Error(Error.PRODUCT_NOT_AVAILABLE_CODE, Error.PRODUCT_NOT_AVAILABLE_MESSAGE));
                throw new ImmediateAcknowledgeAmqpException(ex.getMessage());
            }
            throw new AmqpRejectAndDontRequeueException("failed");
        }
    }

    private boolean isLastAttempt(Message<Order> message) {
        List<MessageHeaders> myAttempts = message.getHeaders().get("deliveryAttempt", List.class);
        Map<String, Object> death = (Map<String, Object>)myAttempts != null && myAttempts.size() > 0 ? myAttempts.get(0) : null;
        return (death != null && (long) death.get("count") > MAX_ATTEMPTS_COUNT);
    }

}
