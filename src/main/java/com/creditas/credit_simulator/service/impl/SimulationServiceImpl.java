package com.creditas.credit_simulator.service.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.credit_simulator.factory.InstallmentCalculationFactory;
import com.creditas.credit_simulator.messaging.publisher.SimulationPublisher;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;
import com.creditas.credit_simulator.service.SimulationPersistenceService;
import com.creditas.credit_simulator.service.SimulationService;
import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.creditas.credit_simulator.util.Utils.calculateAge;
import static com.creditas.credit_simulator.util.Utils.valueConvert;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimulationServiceImpl implements SimulationService {

    private final SimulationPersistenceService persistenceService;
    private final SimulationPublisher simulationPublisher;
    private final InstallmentCalculationFactory factory;
    private final MessagesProperties messagesProperties;

    @Override
    public SimulateLoanResponse simular(SimulateLoanRequest request) throws PublisherException {
        int age = calculateAge(request.getBirthDate(), LocalDate.now());

        double installment = factory.calculate(age, request.getLoanAmount(), request.getPaymentTermMonths());
        double totalAmount = installment * request.getPaymentTermMonths();
        double totalRate = totalAmount - request.getLoanAmount();

        log.info(messagesProperties.getMessageLogValuesCalculated(), totalAmount, installment, totalRate);

        SimulateLoanResponse response = new SimulateLoanResponse();
        response.setMonthlyInstallment(valueConvert(installment));
        response.setTotalAmount(valueConvert(totalAmount));
        response.setTotalRate(valueConvert(totalRate));

        SimulationResultDTO simulationResultDTO = new SimulationResultDTO(request, response);
        simulationPublisher.publish(simulationResultDTO);
        persistenceService.saveSimulation(request, response);

        return response;
    }
}
