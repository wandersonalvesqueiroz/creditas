package com.creditas.credit_simulator.service.impl;

import com.creditas.credit_simulator.config.ConfigProperties;
import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.EmailException;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    private static final double LOAN_AMOUNT = 10000.00;
    private static final BigDecimal MONTHLY_INSTALLMENT = BigDecimal.valueOf(300.50);
    private static final BigDecimal TOTAL_AMOUNT = BigDecimal.valueOf(12000.00);
    private static final BigDecimal TOTAL_RATE = BigDecimal.valueOf(0.20);
    private static final String FROM_EMAIL = "empresa@creditas.com";
    private static final String SUBJECT = "Sua Simulação de Empréstimo";
    private static final String CLIENTE_MAIL = "cliente@gmail.com";
    private static final String BODY_TEMPLATE = "Você simulou um empréstimo de %.2f com parcelas de %.2f...";

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MessagesProperties messagesProperties;

    @Mock
    private ConfigProperties config;

    @InjectMocks
    private EmailServiceImpl emailService;

    private SimulationResultDTO simulation;

    @BeforeEach
    void setUp() {
        simulation = new SimulationResultDTO();
        simulation.setLoanAmount(LOAN_AMOUNT);
        simulation.setMonthlyInstallment(MONTHLY_INSTALLMENT);
        simulation.setTotalAmount(TOTAL_AMOUNT);
        simulation.setTotalRate(TOTAL_RATE);
    }

    @Test
    @DisplayName("Deve enviar o e-mail com sucesso")
    void shouldSendEmailSuccessfullyAndLogSuccessMessage() {

        when(config.getFromEmail()).thenReturn(FROM_EMAIL);
        when(messagesProperties.getMessageSubjectMail()).thenReturn(SUBJECT);
        when(messagesProperties.getMessageBodyMail()).thenReturn(BODY_TEMPLATE);

        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        emailService.sendMail(simulation);

        verify(mailSender, times(1))
                .send(messageCaptor.capture());

        SimpleMailMessage capturedMessage = messageCaptor.getValue();
        assertEquals(FROM_EMAIL, capturedMessage.getFrom());
        assertEquals(CLIENTE_MAIL, Objects.requireNonNull(capturedMessage.getTo())[0]);
        assertEquals(SUBJECT, capturedMessage.getSubject());

        assertEquals(String.format(BODY_TEMPLATE,
                simulation.getLoanAmount(),
                simulation.getMonthlyInstallment(),
                simulation.getTotalAmount(),
                simulation.getTotalRate()
        ), capturedMessage.getText());
    }

    @Test
    @DisplayName("Deve lançar EmailException quando o envio de e-mail falhar")
    void shouldThrowEmailExceptionWhenMailSenderFails() {
        when(config.getFromEmail())
                .thenReturn(FROM_EMAIL);

        assertThrows(EmailException.class,
                () -> emailService.sendMail(simulation)
        );
    }
}