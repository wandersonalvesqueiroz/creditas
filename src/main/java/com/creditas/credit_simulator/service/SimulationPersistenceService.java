package com.creditas.credit_simulator.service;

import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;

public interface SimulationPersistenceService {

    void saveSimulation(SimulateLoanRequest request, SimulateLoanResponse response);
}
