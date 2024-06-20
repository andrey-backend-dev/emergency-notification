package org.example.controller;

import jakarta.mail.MessagingException;
import org.example.dto.response.EmergencyCallsDTO;
import org.example.entity.EmergencyCalls;
import org.example.service.EmergencyCallsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-calls/")
public class EmergencyCallsController {
    private final EmergencyCallsService service;

    EmergencyCallsController(EmergencyCallsService service) {
        this.service = service;
    }

    @PostMapping(path = "call/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmergencyCallsDTO> makeEmergencyCalls(@PathVariable("id") long callerId) throws MessagingException {
        return service.makeEmergencyCalls(callerId);
    }
}
