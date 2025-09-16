package com.creditas.credit_simulator.strategy.impl;

import com.creditas.credit_simulator.config.ConfigProperties;
import com.creditas.credit_simulator.strategy.RateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.creditas.credit_simulator.util.PmtCalculator.calculateMonthlyInstallment;

@Component
@RequiredArgsConstructor
public class UpTo25Strategy implements RateStrategy {

    private final ConfigProperties config;

    @Override
    public boolean isApplicableAgeGroup(int age) {
        return age <= 25;
    }

    @Override
    public double calculateInstallment(double loanAmount, int paymentTermMonths) {
        double monthlyRate = config.getAnnualRateUpTo25() / 12;

        return calculateMonthlyInstallment(loanAmount, monthlyRate, paymentTermMonths);
    }
}
