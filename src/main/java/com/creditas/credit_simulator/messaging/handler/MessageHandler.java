package com.creditas.credit_simulator.messaging.handler;

import com.creditas.credit_simulator.model.dto.SimulationResultDTO;

public interface MessageHandler {

    void handle(SimulationResultDTO message);
}
