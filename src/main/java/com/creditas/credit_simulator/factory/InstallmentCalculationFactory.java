package com.creditas.credit_simulator.factory;

import com.creditas.credit_simulator.config.MessagesProperties;
import com.creditas.credit_simulator.exception.NoApplicableAgeStrategyException;
import com.creditas.credit_simulator.strategy.RateStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InstallmentCalculationFactory {

    private final List<RateStrategy> strategies;
    private final MessagesProperties messagesProperties;

    @Cacheable(
            value = "installmentCache",
            key = "#age + '-' + #loanAmount + '-' + #paymentTermMonths"
    )
    public double calculate(int age, double loanAmount, int paymentTermMonths) {

        log.info(messagesProperties.getMessageLogAgeFactory(), age, loanAmount, paymentTermMonths);

        return strategies.stream()
                .filter(strategy -> strategy.isApplicableAgeGroup(age))
                .findFirst()
                .orElseThrow(() ->  new NoApplicableAgeStrategyException(age))
                .calculateInstallment(loanAmount, paymentTermMonths);
    }
}
