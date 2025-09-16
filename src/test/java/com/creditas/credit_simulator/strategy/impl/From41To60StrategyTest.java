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
class From41To60StrategyTest {

    @Mock
    private ConfigProperties config;

    @InjectMocks
    private From41To60Strategy strategy;

    @Test
    @DisplayName("Deve retornar true para idades entre 41 e 60 anos")
    void shouldReturnTrueForAgesFrom41To60() {
        assertTrue(strategy.isApplicableAgeGroup(41));
        assertTrue(strategy.isApplicableAgeGroup(53));
        assertTrue(strategy.isApplicableAgeGroup(60));
    }

    @Test
    @DisplayName("Deve retornar false para idades abaixo de 41 e acima 60 anos")
    void shouldReturnFalseForAgesBelow41AndAbove60() {
        assertFalse(strategy.isApplicableAgeGroup(40));
        assertFalse(strategy.isApplicableAgeGroup(61));
    }

    @Test
    @DisplayName("Deve calcular a parcela usando a taxa de juros para o grupo entre 41 e 60 anos")
    void shouldCalculateInstallmentUsingCorrectRate() {
        double loanAmount = 100.0;
        int paymentTermMonths = 10;
        double annualRate = 0.02;

        when(config.getAnnualRateFrom41To60())
                .thenReturn(annualRate);

        BigDecimal expectedInstallment = BigDecimal.valueOf(10.09);
        double actualInstallment = strategy.calculateInstallment(loanAmount, paymentTermMonths);

        assertEquals(annualRate, config.getAnnualRateFrom41To60());
        assertEquals(expectedInstallment, BigDecimal.valueOf(actualInstallment).setScale(2, RoundingMode.HALF_UP));
    }
}