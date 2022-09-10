package com.advanced.message.training.rabbit.infrastructure;

import com.advanced.message.training.rabbit.application.Error;
import com.advanced.message.training.rabbit.application.Order;
import com.advanced.message.training.rabbit.application.OrderService;
import com.advanced.message.training.rabbit.application.ProductAmountExceededException;
import com.advanced.message.training.rabbit.application.ProductNotAvailableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.ImmediateAcknowledgeAmqpException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class Listener {
    private final int MAX_ATTEMPTS_COUNT = 5;

    private final OrderService orderService;
    private final FailProducer failProducer;

    @SneakyThrows
    @StreamListener(ListenerProcessor.INPUT)
    public void processEvent(Message<Order> message) {
        Order order = message.getPayload();
        log.info("Get order with payload == {} \n headers == {} \n", order, message.getHeaders());
        try {
            orderService.process(order);
        } catch (ProductNotAvailableException ex) {
            throw new ImmediateAcknowledgeAmqpException(ex.getMessage());
        } catch (ProductAmountExceededException ex) {
            if (isLastAttempt(message)) {
                log.info("Attempts are exceed limit, give up");
                failProducer.sendFailedEvent(new Error(Error.PRODUCT_NOT_AVAILABLE_CODE, Error.PRODUCT_NOT_AVAILABLE_MESSAGE));
                throw new ImmediateAcknowledgeAmqpException(ex.getMessage());
            }
            log.info("Attempts are left, retry");
            throw new RuntimeException("failed");
        }
    }

    private boolean isLastAttempt(Message<Order> message) {
        List<MessageHeaders> myAttempts = message.getHeaders().get("x-death", List.class);
        if (myAttempts != null && myAttempts.size() > 0) {
            Map<String, Object> death = (Map<String, Object>)myAttempts.get(0);
            Long count = (Long) death.get("count");
            return count > MAX_ATTEMPTS_COUNT;
        }
        return false;
    }

}
