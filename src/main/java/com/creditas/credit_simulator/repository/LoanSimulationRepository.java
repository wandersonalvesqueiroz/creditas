package com.creditas.credit_simulator.repository;

import com.creditas.credit_simulator.model.entity.LoanSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanSimulationRepository extends JpaRepository<LoanSimulation, Long> {
}
