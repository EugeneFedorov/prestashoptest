package com.prestashop.exceptions;

public class RandomEnumGeneratorException extends RuntimeException {

    public RandomEnumGeneratorException() {
        super();
    }

    public RandomEnumGeneratorException(String message) {
        super(message);
    }

    public RandomEnumGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RandomEnumGeneratorException(Throwable cause) {
        super(cause);
    }

}
