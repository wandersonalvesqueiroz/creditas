package com.creditas.credit_simulator.service;

import com.creditas.credit_simulator.model.dto.SimulationResultDTO;

public interface EmailService {
    void sendMail(SimulationResultDTO response);
}
