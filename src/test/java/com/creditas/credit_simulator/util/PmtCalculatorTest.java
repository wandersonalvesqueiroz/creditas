package com.creditas.credit_simulator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.creditas.credit_simulator.util.PmtCalculator.calculateMonthlyInstallment;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PmtCalculatorTest {
    @Test
    @DisplayName("Deve calcular a parcela mensal corretamente")
    void shouldCalculateMonthlyInstallmentCorrectlyForStandardCase() {
        double loanAmount = 100.0;
        double monthlyRate = 0.10;
        int paymentTermMonths = 10;

        String expected = "16,27";
        double actualPmt = calculateMonthlyInstallment(loanAmount, monthlyRate, paymentTermMonths);
        String actual = String.format("%.2f",actualPmt);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Deve retornar zero quando o valor do empréstimo for zero")
    void shouldReturnZeroWhenLoanAmountIsZero() {
        double loanAmount = 0.0;
        double monthlyRate = 0.10;
        int paymentTermMonths = 10;

        double expectedPmt = 0.0;
        double actualPmt = calculateMonthlyInstallment(loanAmount, monthlyRate, paymentTermMonths);

        assertEquals(expectedPmt, actualPmt);
    }
}