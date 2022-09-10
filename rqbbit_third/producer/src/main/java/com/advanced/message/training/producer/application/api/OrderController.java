package com.advanced.message.training.producer.application.api;

import com.advanced.message.training.producer.application.service.OrderService;
import com.advanced.message.training.producer.application.service.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody Order order) {
        service.makeOrder(order);
    }

}
