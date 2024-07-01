package org.example.controller;

import jakarta.mail.MessagingException;
import org.example.dto.response.EmergencyCallDTO;
import org.example.service.EmergencyCallService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/emergency-calls/")
public class EmergencyCallController {
    private final EmergencyCallService service;

    EmergencyCallController(EmergencyCallService service) {
        this.service = service;
    }

    @PostMapping(path = "call/", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EmergencyCallDTO> makeEmergencyCalls(Principal principal) throws MessagingException, TelegramApiException {
        return service.makeEmergencyCalls(principal.getName());
    }
}
