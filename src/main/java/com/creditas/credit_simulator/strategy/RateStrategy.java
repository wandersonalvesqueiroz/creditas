package com.creditas.credit_simulator.strategy;

public interface RateStrategy {

    boolean isApplicableAgeGroup(int age);

    double calculateInstallment(double loanAmount, int paymentTermMonths);
}
