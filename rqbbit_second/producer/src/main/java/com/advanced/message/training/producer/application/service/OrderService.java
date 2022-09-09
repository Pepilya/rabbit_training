package com.advanced.message.training.producer.application.service;

import com.advanced.message.training.producer.application.service.model.Order;
import com.advanced.message.training.producer.infrastructure.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final ConcurrentHashMap<Integer, Order> map = new ConcurrentHashMap<>();

    private final Producer producer;

    public void registerUser(Order order) {
        map.put(order.getOrderId(), order);
        producer.publishEvent(order);
    }

}
