package com.creditas.credit_simulator.exception;

import com.creditas.credit_simulator.config.MessagesProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoApplicableAgeStrategyException extends RuntimeException {

    private static MessagesProperties messagesProperties;

    @Autowired
    public NoApplicableAgeStrategyException(MessagesProperties messagesProperties) {
        NoApplicableAgeStrategyException.messagesProperties = messagesProperties;
    }

    public NoApplicableAgeStrategyException(int age) {
        super(String.format(messagesProperties.getMessageErrorAgeStrategy(), age));
    }
}
