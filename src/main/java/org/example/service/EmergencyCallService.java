package org.example.service;

import jakarta.mail.MessagingException;
import org.example.dto.response.EmergencyCallDTO;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Validated
public interface EmergencyCallService {
    List<EmergencyCallDTO> makeEmergencyCalls(long caller_id) throws MessagingException, TelegramApiException;
}
