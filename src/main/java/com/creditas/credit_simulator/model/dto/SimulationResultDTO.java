package com.creditas.credit_simulator.model.dto;

import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class SimulationResultDTO {

    public SimulationResultDTO(SimulateLoanRequest request, SimulateLoanResponse response) {
        this.loanAmount = request.getLoanAmount();
        this.birthDate = request.getBirthDate();
        this.paymentTermMonths = request.getPaymentTermMonths();
        this.monthlyInstallment = response.getMonthlyInstallment();
        this.totalAmount = response.getTotalAmount();
        this.totalRate = response.getTotalRate();
    }

    private double loanAmount;
    private Date birthDate;
    private int paymentTermMonths;
    private BigDecimal monthlyInstallment;
    private BigDecimal totalAmount;
    private BigDecimal totalRate;
}
