package com.advanced.message.training.rabbit.application;

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
