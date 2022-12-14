package com.example.Parking.controller;


import com.example.Parking.dto.requestWrapper.AgentAcountDTO;
import com.example.Parking.model.Agent;
import com.example.Parking.service.impl.AgentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("agent/")
@CrossOrigin("*")
@Data
@AllArgsConstructor
public class AgentController {

    //variable
    @Autowired
    private AgentServiceImpl agentService;

    @GetMapping("creatgg")
    public String tryingConfig(){
        return "hello ";
    }


    @GetMapping("{name}")
    ResponseEntity<Optional<Agent>> getAgentByName(@PathVariable String name ){
        Optional<Agent> Agent = agentService.getByName(name);
        return ResponseEntity.ok(Agent);
    }
    @GetMapping(value = "all")
    ResponseEntity<List<Agent>> getAllAgent(){
        List<Agent> agents = agentService.getAllAgent();
        return ResponseEntity.ok(agents);
    }

    @PostMapping( value = "creat",produces = "application/json")
    public ResponseEntity<Agent> creatAgent(@RequestBody AgentAcountDTO agentAcountDTO){

        Agent agent = agentService.creatAgent(agentAcountDTO.getAgentDto(),agentAcountDTO.getAccountDtos(),agentAcountDTO.getAdressDto());
        System.out.println(" l'agent "+agentAcountDTO.getAgentDto());

        return ResponseEntity.ok(agent);
    }


}
