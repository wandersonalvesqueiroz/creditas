package com.creditas.credit_simulator.service.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.credit_simulator.factory.InstallmentCalculationFactory;
import com.creditas.credit_simulator.messaging.publisher.SimulationPublisher;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import com.creditas.credit_simulator.service.SimulationPersistenceService;
import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationServiceImplTest {

    private static final Date BIRTH_DATE = Date.from(Instant.parse("1990-01-01T00:00:00Z"));
    private static final double LOAN_AMOUNT = 100.00;
    private static final int PAYMENT_TERM_MONTHS = 10;
    private static final String MESSAGE_LOG_VALUES_CALCULATED = "Valor total a ser pago: {}. Valor das parcelas mensais: {}. Total de juros pagos: {}.";
    public static final double MONTHLY_INSTALLMENT = 900.0;
    public static final int AGE = 35;
    public static final String ERROR_PUBLISH_MENSAGE = "Erro ao publicar mensagem";
    public static final String ERROR_PERSIST_MESSAGE = "Erro ao salvar no banco de dados";

    @Mock
    private SimulationPersistenceService persistenceService;

    @Mock
    private SimulationPublisher simulationPublisher;

    @Mock
    private InstallmentCalculationFactory factory;

    @Mock
    private MessagesProperties messagesProperties;

    @InjectMocks
    private SimulationServiceImpl simulationService;

    private SimulateLoanRequest request;

    @BeforeEach
    void setUp() {
        request = new SimulateLoanRequest();
        request.setBirthDate(BIRTH_DATE);
        request.setLoanAmount(LOAN_AMOUNT);
        request.setPaymentTermMonths(PAYMENT_TERM_MONTHS);

        when(messagesProperties.getMessageLogValuesCalculated())
                .thenReturn(MESSAGE_LOG_VALUES_CALCULATED);
    }

    @Test
    @DisplayName("Deve simular um empréstimo com sucesso e chamar os serviços de persistência e publicação")
    void shouldSimulateLoanSuccessfullyAndCallDependencies() throws PublisherException {
        when(factory.calculate(anyInt(), anyDouble(), anyInt()))
                .thenReturn(MONTHLY_INSTALLMENT);

        ArgumentCaptor<SimulationResultDTO> publisherCaptor = ArgumentCaptor.forClass(SimulationResultDTO.class);
        ArgumentCaptor<SimulateLoanResponse> persistenceResponseCaptor = ArgumentCaptor.forClass(SimulateLoanResponse.class);

        SimulateLoanResponse response = simulationService.simular(request);

        assertNotNull(response);

        verify(factory).calculate(AGE, LOAN_AMOUNT, PAYMENT_TERM_MONTHS);

        verify(simulationPublisher).publish(publisherCaptor.capture());
        SimulationResultDTO capturedDto = publisherCaptor.getValue();
        assertEquals(request.getLoanAmount(), capturedDto.getLoanAmount());
        assertEquals(request.getBirthDate(), capturedDto.getBirthDate());
        assertEquals(request.getPaymentTermMonths(), capturedDto.getPaymentTermMonths());
        assertEquals(response.getMonthlyInstallment(), capturedDto.getMonthlyInstallment());
        assertEquals(response.getTotalAmount(), capturedDto.getTotalAmount());
        assertEquals(response.getTotalRate(), capturedDto.getTotalRate());

        verify(persistenceService).saveSimulation(eq(request), persistenceResponseCaptor.capture());
        SimulateLoanResponse capturedResponse = persistenceResponseCaptor.getValue();
        assertEquals(response.getMonthlyInstallment(), capturedResponse.getMonthlyInstallment());
    }

    @Test
    @DisplayName("Deve lançar PublisherException se a publicação falhar")
    void shouldThrowPublisherExceptionIfPublishingFails() throws PublisherException {
        when(factory.calculate(anyInt(), anyDouble(), anyInt()))
                .thenReturn(MONTHLY_INSTALLMENT);

        doThrow(new PublisherException(ERROR_PUBLISH_MENSAGE))
                .when(simulationPublisher)
                .publish(any(SimulationResultDTO.class));

        assertThrows(PublisherException.class,
                () -> simulationService.simular(request));

        verify(simulationPublisher, times(1))
                .publish(any());
    }

    @Test
    @DisplayName("Deve lançar PersistenceException se a persistência falhar")
    void shouldThrowPersistenceExceptionIfSavingFails() throws PersistenceException {
        when(factory.calculate(anyInt(), anyDouble(), anyInt()))
                .thenReturn(MONTHLY_INSTALLMENT);

        doThrow(new PersistenceException(ERROR_PERSIST_MESSAGE))
                .when(persistenceService)
                .saveSimulation(any(), any());

        assertThrows(PersistenceException.class,
                () -> simulationService.simular(request));
    }
}