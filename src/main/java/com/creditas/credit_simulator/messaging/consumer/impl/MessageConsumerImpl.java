package com.creditas.credit_simulator.messaging.consumer.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.ConsumerException;
import com.creditas.credit_simulator.messaging.consumer.MessageConsumer;
import com.creditas.credit_simulator.messaging.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumerImpl implements MessageConsumer {

    private final MessagesProperties messagesProperties;

    @Override
    public void consume(MessageHandler handler) throws ConsumerException {
        try {
            log.info(messagesProperties.getMessageLogConsume());
            //Executa o consumo da fila independente do tipo
        } catch (Exception e) {
            log.error(messagesProperties.getMessageLogErrorConsume(), e);
            throw new ConsumerException(messagesProperties.getMessageErrorConsume(), e);
        }
    }
}
