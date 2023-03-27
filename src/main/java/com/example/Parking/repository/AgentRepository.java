package com.example.Parking.repository;

import com.example.Parking.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByName(String name);

    @Override
    Optional<Agent> findById(Long aLong);

    @Query("SELECT a FROM Agent a where a.siretNumber=:numSiret")
    Optional<Agent> findAgentByNumSire(String numSiret);

    @Query("SELECT e from Agent e where e.email=:agentEmail")
    Optional<Agent> findByEmail(String agentEmail);

}