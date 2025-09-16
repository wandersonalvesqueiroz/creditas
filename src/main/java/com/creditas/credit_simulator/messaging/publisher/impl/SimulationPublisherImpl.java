package com.creditas.credit_simulator.messaging.publisher.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.credit_simulator.messaging.publisher.SimulationPublisher;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimulationPublisherImpl implements SimulationPublisher {

    private final MessagesProperties messagesProperties;

    @Override
    @Async
    public void publish(SimulationResultDTO message) throws PublisherException {
        try {
            // TODO implementar publicação na fila
            log.info(messagesProperties.getMessageLogPublishSimulation(), message);
        } catch (Exception e) {
            log.info(messagesProperties.getMessageLogErrorPublishSimulation(), message);
            throw new PublisherException(messagesProperties.getMessageErrorPublish(), e);
        }

    }
}
