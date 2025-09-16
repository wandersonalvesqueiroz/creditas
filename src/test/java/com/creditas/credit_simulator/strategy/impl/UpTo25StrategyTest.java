package com.creditas.credit_simulator.strategy.impl;

import com.creditas.credit_simulator.config.ConfigProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpTo25StrategyTest {

    @Mock
    private ConfigProperties config;

    @InjectMocks
    private UpTo25Strategy strategy;

    @Test
    @DisplayName("Deve retornar true para idades abaixo de 25 anos")
    void shouldReturnTrueForAgesUpTo25() {
        assertTrue(strategy.isApplicableAgeGroup(1));
        assertTrue(strategy.isApplicableAgeGroup(10));
        assertTrue(strategy.isApplicableAgeGroup(25));
    }

    @Test
    @DisplayName("Deve retornar false para idades de 26 anos ou mais")
    void shouldReturnFalseForAgesAbove25() {
        assertFalse(strategy.isApplicableAgeGroup(26));
        assertFalse(strategy.isApplicableAgeGroup(30));
    }

    @Test
    @DisplayName("Deve calcular a parcela usando a taxa de juros para o grupo abaixo de 25 anos")
    void shouldCalculateInstallmentUsingCorrectRate() {
        double loanAmount = 100.0;
        int paymentTermMonths = 10;
        double annualRate = 0.05;

        when(config.getAnnualRateUpTo25())
                .thenReturn(annualRate);

        BigDecimal expectedInstallment = BigDecimal.valueOf(10.23);
        double actualInstallment = strategy.calculateInstallment(loanAmount, paymentTermMonths);

        assertEquals(annualRate, config.getAnnualRateUpTo25());
        assertEquals(expectedInstallment, BigDecimal.valueOf(actualInstallment).setScale(2, RoundingMode.HALF_UP));
    }
}