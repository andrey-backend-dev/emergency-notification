package org.example.service;

import jakarta.mail.MessagingException;
import org.example.dto.response.EmergencyCallsDTO;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface EmergencyCallsService {
    List<EmergencyCallsDTO> makeEmergencyCalls(long caller_id) throws MessagingException;

    void sendEmail(String address, String message, String receiver, String callerName, String callerAddress) throws MessagingException;
}
