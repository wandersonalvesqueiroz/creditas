package com.creditas.credit_simulator.service.impl;

import com.creditas.credit_simulator.config.ConfigProperties;
import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.EmailException;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import com.creditas.credit_simulator.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final MessagesProperties messagesProperties;
    private final ConfigProperties config;

    @Override
    public void sendMail(SimulationResultDTO simulation) {
        /* TODO possível melhoria no endpoint para receber o e-mail do cliente
        ou outra forma de recuperar o e-mail do cliente
        final var emailClient = simulation.getCustomerEmail();
         */

        final var emailClient = "cliente@gmail.com";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(config.getFromEmail());
            message.setTo(emailClient);
            message.setSubject(messagesProperties.getMessageSubjectMail());
            message.setText(buildEmailBody(simulation));

            mailSender.send(message);

            log.info(messagesProperties.getMessageLogSuccessMail(), emailClient);
        } catch (Exception e) {
            log.error(messagesProperties.getMessageLogErrorMail(), emailClient, e.getMessage());
            throw new EmailException(messagesProperties.getMessageErrorMail(), e);
        }
    }

    private String buildEmailBody(SimulationResultDTO simulation) {
        return String.format(messagesProperties.getMessageBodyMail(),
                simulation.getLoanAmount(),
                simulation.getMonthlyInstallment(),
                simulation.getTotalAmount(),
                simulation.getTotalRate()
        );
    }
}