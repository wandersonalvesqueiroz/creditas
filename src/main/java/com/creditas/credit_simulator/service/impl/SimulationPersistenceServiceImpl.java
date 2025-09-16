package com.creditas.credit_simulator.service.impl;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.model.entity.LoanSimulation;
import com.creditas.credit_simulator.repository.LoanSimulationRepository;
import com.creditas.credit_simulator.service.SimulationPersistenceService;
import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimulationPersistenceServiceImpl implements SimulationPersistenceService {

    private final LoanSimulationRepository repository;
    private final MessagesProperties messagesProperties;

    @Override
    @Async
    public void saveSimulation(SimulateLoanRequest request, SimulateLoanResponse response) {
        try {
            LoanSimulation simulation = new LoanSimulation();
            simulation.setMonthlyInstallment(response.getMonthlyInstallment());
            simulation.setTotalAmount(response.getTotalAmount());
            simulation.setTotalRate(response.getTotalRate());
            simulation.setLoanAmount(request.getLoanAmount());
            simulation.setPaymentTermMonths(request.getPaymentTermMonths());
            simulation.setBirthDate(request.getBirthDate());

            log.info(messagesProperties.getMessageLogSaveSimulationRepository(), simulation);

            repository.save(simulation);
        } catch (Exception e) {
            log.error(messagesProperties.getMessageLogErrorPersistence(), e);
            throw new PersistenceException(messagesProperties.getMessageErrorPersistence(), e);
        }
    }
}
