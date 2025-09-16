package com.creditas.credit_simulator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsTest {

    private static final Date BIRTH_DATE = Date.from(Instant.parse("1990-01-01T00:00:00Z"));
    private static final LocalDate DATA_REFERENCIA = LocalDate.of(2025, 9, 14);
    private static final LocalDate LOCAL_DATE = LocalDate.of(2023, 10, 26);
    private static final Date DATE = Date.from(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant());
    private static final double DOUBLE_UP = 123.456;
    private static final BigDecimal BIG_DECIMAL_UP = BigDecimal.valueOf(123.46);
    private static final double DOUBLE_DOWN = 123.454;
    private static final BigDecimal BIG_DECIMAL_DOWN = BigDecimal.valueOf(123.45);
    private static final double DOUBLE = 1000.0;
    private static final String BIG_DECIMAL_2F = "1000.00";
    private static final double DOUBLE_NEGATIVE = -123.456;
    private static final BigDecimal BIG_DECIMAL_NEGATIVE = BigDecimal.valueOf(-123.46);
    private static final double DOUBLE_ZERO = 0.0;
    private static final String ZERO = "0.00";

    @Test
    @DisplayName("Deve calcular a idade corretamente a partir de uma data de nascimento")
    void shouldCalculateAgeCorrectlyFromBirthDate() {
        int expectedAge = 35;
        int actualAge = Utils.calculateAge(BIRTH_DATE, DATA_REFERENCIA);

        assertEquals(expectedAge, actualAge);
    }

    @Test
    @DisplayName("Deve retornar 0 anos se a data de nascimento for hoje")
    void shouldReturnZeroIfBirthDateIsToday() {
        Date date = new Date();

        int expectedAge = 0;
        int actualAge = Utils.calculateAge(date, LocalDate.now());

        assertEquals(expectedAge, actualAge);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se a data de nascimento for nula")
    void shouldThrowExceptionIfBirthDateIsNull() {
        assertThrows(NullPointerException.class, () -> {
            Utils.calculateAge(null, LocalDate.now());
        });
    }

    @Test
    @DisplayName("Deve converter Date para LocalDate corretamente")
    void shouldConvertDateToLocalDateCorrectly() {
        LocalDate actual = Utils.dateToLocaldate(DATE);

        assertEquals(LOCAL_DATE, actual);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se a data de entrada for nula")
    void shouldThrowExceptionWhenInputDateIsNull() {
        assertThrows(NullPointerException.class, () -> {
            Utils.dateToLocaldate(null);
        });
    }

    @Test
    @DisplayName("Deve converter double para BigDecimal arredondando para cima (HALF_UP)")
    void shouldRoundUpDoubleToBigDecimal() {
        BigDecimal actual = Utils.valueConvert(DOUBLE_UP);

        assertEquals(BIG_DECIMAL_UP, actual);
    }

    @Test
    @DisplayName("Deve converter double para BigDecimal arredondando para baixo (HALF_UP)")
    void shouldRoundDownDoubleToBigDecimal() {
        BigDecimal actual = Utils.valueConvert(DOUBLE_DOWN);

        assertEquals(BIG_DECIMAL_DOWN, actual);
    }

    @Test
    @DisplayName("Deve adicionar casas decimais a um valor inteiro")
    void shouldAddDecimalPlacesToAnIntegerValue() {
        String actual = Utils.valueConvert(DOUBLE).toString();

        assertEquals(BIG_DECIMAL_2F, actual);
    }

    @Test
    @DisplayName("Deve converter e arredondar valores negativos corretamente")
    void shouldConvertAndRoundNegativeValuesCorrectly() {
        BigDecimal actual = Utils.valueConvert(DOUBLE_NEGATIVE);

        assertEquals(BIG_DECIMAL_NEGATIVE, actual);
    }

    @Test
    @DisplayName("Deve retornar 0.00 para o valor zero")
    void shouldReturnZeroWithTwoDecimalPlaces() {
        String actual = Utils.valueConvert(DOUBLE_ZERO).toString();

        assertEquals(ZERO, actual);
    }
}