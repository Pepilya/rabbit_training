package com.advanced.message.training.rabbit.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    public final static String MAX_PRODUCT_AMOUNT_EXCEEDED_CODE = "101";
    public final static String MAX_PRODUCT_AMOUNT_EXCEEDED_MESSAGE = "Maximum value of product is exceeded";

    public final static String PRODUCT_NOT_AVAILABLE_CODE = "102";
    public final static String PRODUCT_NOT_AVAILABLE_MESSAGE= "Maximum value of product is exceeded";

    private String code;
    private String message;

}
