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
class Above60StrategyTest {

    @Mock
    private ConfigProperties config;

    @InjectMocks
    private Above60Strategy strategy;

    @Test
    @DisplayName("Deve retornar true para idades acima de 60 anos")
    void shouldReturnTrueForAgesAbove60() {
        assertTrue(strategy.isApplicableAgeGroup(61));
        assertTrue(strategy.isApplicableAgeGroup(75));
        assertTrue(strategy.isApplicableAgeGroup(100));
    }

    @Test
    @DisplayName("Deve retornar false para idades de 60 anos ou menos")
    void shouldReturnFalseForAges60OrBelow() {
        assertFalse(strategy.isApplicableAgeGroup(60));
        assertFalse(strategy.isApplicableAgeGroup(59));
        assertFalse(strategy.isApplicableAgeGroup(30));
    }

    @Test
    @DisplayName("Deve calcular a parcela usando a taxa de juros para o grupo acima de 60 anos")
    void shouldCalculateInstallmentUsingCorrectRate() {
        double loanAmount = 100.0;
        int paymentTermMonths = 10;
        double annualRate = 0.04;

        when(config.getAnnualRateAbove60())
                .thenReturn(annualRate);

        BigDecimal expectedInstallment = BigDecimal.valueOf(10.18);
        double actualInstallment = strategy.calculateInstallment(loanAmount, paymentTermMonths);

        assertEquals(annualRate, config.getAnnualRateAbove60());
        assertEquals(expectedInstallment, BigDecimal.valueOf(actualInstallment).setScale(2, RoundingMode.HALF_UP));
    }
}