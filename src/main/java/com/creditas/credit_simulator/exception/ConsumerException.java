package com.creditas.credit_simulator.exception;

public class ConsumerException extends Exception {

    public ConsumerException(String message) {
        super(message);
    }

    public ConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}