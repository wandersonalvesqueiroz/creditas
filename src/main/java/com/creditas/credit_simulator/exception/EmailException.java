package com.creditas.credit_simulator.exception;

import org.springframework.mail.MailException;

public class EmailException extends MailException {

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}