package com.creditas.credit_simulator.service;

import com.creditas.credit_simulator.exception.PublisherException;
import com.creditas.model.SimulateLoanRequest;
import com.creditas.model.SimulateLoanResponse;

public interface SimulationService {

    SimulateLoanResponse simular(SimulateLoanRequest request) throws PublisherException;
}
