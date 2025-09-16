package com.creditas.credit_simulator.controller;

import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.credit_simulator.service.SimulationService;
import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulate-loan")
@RequiredArgsConstructor
public class LoanSimulationController {

    private final SimulationService service;

    @PostMapping
    public SimulateLoanResponse simulateLoan(@RequestBody SimulateLoanRequest request) throws PublisherException {
        return service.simular(request);
    }
}
