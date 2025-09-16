package com.creditas.credit_simulator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static int calculateAge(Date birthDate, LocalDate dataReferencia) {
        return Period.between(dateToLocaldate(birthDate), dataReferencia).getYears();
    }

    public static LocalDate dateToLocaldate(Date data) {
        Instant instant = data.toInstant();
        ZoneId defaultZoneId = ZoneId.systemDefault();
       return instant.atZone(defaultZoneId).toLocalDate();
    }

    public static BigDecimal valueConvert(double value){
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }
}
