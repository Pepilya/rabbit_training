package com.advanced.message.training.producer.application.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderId;
    private Integer productId;
    private Integer amount;
    private Status status = Status.PENDING;


    public enum Status {
        PENDING,
        APPROVED,
        DECLINED
    }

}
