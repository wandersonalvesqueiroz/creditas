package com.creditas.credit_simulator.strategy.impl;

import com.creditas.credit_simulator.config.ConfigProperties;
import com.creditas.credit_simulator.strategy.RateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.creditas.credit_simulator.util.PmtCalculator.calculateMonthlyInstallment;

@Component
@RequiredArgsConstructor
public class From41To60Strategy implements RateStrategy {

    private final ConfigProperties config;

    @Override
    public boolean isApplicableAgeGroup(int age) {
        return age > 40 && age <= 60;
    }

    @Override
    public double calculateInstallment(double loanAmount, int paymentTermMonths) {
        double monthlyRate = config.getAnnualRateFrom41To60() / 12;

        return calculateMonthlyInstallment(loanAmount, monthlyRate, paymentTermMonths);
    }
}
