package com.advanced.message.training.rabbit.application;

public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException() {
        super();
    }

    public ProductNotAvailableException(String message) {
        super(message);
    }

    public ProductNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotAvailableException(Throwable cause) {
        super(cause);
    }

    protected ProductNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
