package com.example.Parking.controller;


import com.example.Parking.dto.requestWrapper.AgentAcountDTO;
import com.example.Parking.model.Agent;
import com.example.Parking.repository.AgentRepository;
import com.example.Parking.service.impl.AgentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AgentController {
    private  AgentRepository agentRepository;


    //variable
    private AgentServiceImpl agentService;

    public AgentController(AgentServiceImpl agentService,AgentRepository agentRepository) {
        this.agentService = agentService;
        this.agentRepository =agentRepository;
    }

    // Logger logger = LoggerFactory.getLogger(AgentController.class);

    @GetMapping("creatgg")
    public String tryingConfig(){
        log.info("bismark");
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
        log.info(" "+agentAcountDTO.getAgentDto());
        Agent agent = agentService.creatAgent(agentAcountDTO.getAgentDto(),agentAcountDTO.getAccountDtos(),agentAcountDTO.getAdressDto());
        return ResponseEntity.ok(agent);
    }
}
