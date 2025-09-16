package com.creditas.credit_simulator.util;

public class PmtCalculator {

    public static double calculateMonthlyInstallment(double loanAmount, double monthlyRate, int paymentTermMonths) {
        return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -paymentTermMonths));
    }
}
