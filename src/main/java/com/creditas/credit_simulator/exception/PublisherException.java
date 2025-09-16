package com.creditas.credit_simulator.exception;

public class PublisherException extends Exception {

    public PublisherException(String message) {
        super(message);
    }

    public PublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}