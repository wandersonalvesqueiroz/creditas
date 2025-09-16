package com.creditas.credit_simulator.messaging.publisher;

import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.credit_simulator.model.dto.SimulationResultDTO;

public interface SimulationPublisher {
    void publish(SimulationResultDTO simulationResultDTO) throws PublisherException;
}
