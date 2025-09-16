package com.creditas.credit_simulator.messaging.consumer;

import com.creditas.credit_simulator.exception.ConsumerException;
import com.creditas.credit_simulator.messaging.handler.MessageHandler;

public interface MessageConsumer {

    void consume(MessageHandler message) throws ConsumerException;
}
