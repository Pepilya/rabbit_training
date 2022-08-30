package com.advanced.message.training.rabbit.application;

public class ProductAmountExceededException extends RuntimeException {

    public ProductAmountExceededException() {
        super();
    }

    public ProductAmountExceededException(String message) {
        super(message);
    }

    public ProductAmountExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductAmountExceededException(Throwable cause) {
        super(cause);
    }

    protected ProductAmountExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
