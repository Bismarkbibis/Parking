package com.example.Parking.dto;

import com.example.Parking.model.Adress;
import com.example.Parking.model.Agent;
import com.example.Parking.outils.MappClass;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link Agent} entity
 */
@Data
public class AgentDto implements Serializable {

    private final String name;

    private final String number;

    private final String siretNumber;

    private final String email;

    // mapAccountToDTO
    private static AgentDto mapAgentToDTO(Agent agent){
       AgentDto agentDto= MappClass.mapper.map(agent,AgentDto.class);
        return agentDto;
    }

    //mapAccountToeEntity
    private static Agent mapAgentToEntity(AgentDto agentDto){
        Agent agent=MappClass.mapper.map(agentDto,Agent.class);
        return agent;
    }

}