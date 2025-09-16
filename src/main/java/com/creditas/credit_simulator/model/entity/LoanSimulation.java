package com.creditas.credit_simulator.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "loan_simulation")
@Getter
@Setter
public class LoanSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monthlyInstallment;
    private BigDecimal totalAmount;
    private BigDecimal totalRate;

    private Double loanAmount;
    private Integer paymentTermMonths;
    private Date birthDate;
}
