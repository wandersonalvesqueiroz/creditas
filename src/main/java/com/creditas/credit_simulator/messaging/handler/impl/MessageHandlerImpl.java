package com.creditas.credit_simulator.messaging.handler.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.messaging.handler.MessageHandler;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import com.creditas.credit_simulator.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {

    private final EmailService emailService;
    private final MessagesProperties messagesProperties;

    @Override
    public void handle(SimulationResultDTO message) {
        log.info(messagesProperties.getMessageLogProcess(), message);
        emailService.sendMail(message);
    }
}
