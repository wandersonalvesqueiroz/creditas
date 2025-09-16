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
class From26To40StrategyTest {

    @Mock
    private ConfigProperties config;

    @InjectMocks
    private From26To40Strategy strategy;

    @Test
    @DisplayName("Deve retornar true para idades entre 26 e 40 anos")
    void shouldReturnTrueForAgesFrom26To40() {
        assertTrue(strategy.isApplicableAgeGroup(26));
        assertTrue(strategy.isApplicableAgeGroup(33));
        assertTrue(strategy.isApplicableAgeGroup(40));
    }

    @Test
    @DisplayName("Deve retornar false para idades abaixo de 26 e acima 40 anos")
    void shouldReturnFalseForAgesBelow26AndAbove40() {
        assertFalse(strategy.isApplicableAgeGroup(25));
        assertFalse(strategy.isApplicableAgeGroup(41));
    }

    @Test
    @DisplayName("Deve calcular a parcela usando a taxa de juros para o grupo entre 26 e 40 anos")
    void shouldCalculateInstallmentUsingCorrectRate() {
        double loanAmount = 100.0;
        int paymentTermMonths = 10;
        double annualRate = 0.03;

        when(config.getAnnualRateFrom26To40())
                .thenReturn(annualRate);

        BigDecimal expectedInstallment = BigDecimal.valueOf(10.14);
        double actualInstallment = strategy.calculateInstallment(loanAmount, paymentTermMonths);

        assertEquals(annualRate, config.getAnnualRateFrom26To40());
        assertEquals(expectedInstallment, BigDecimal.valueOf(actualInstallment).setScale(2, RoundingMode.HALF_UP));
    }
}