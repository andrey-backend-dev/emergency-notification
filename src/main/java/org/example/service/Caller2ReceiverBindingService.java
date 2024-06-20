package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.dto.request.Caller2ReceiverBindingCreateDTO;
import org.example.dto.response.Caller2ReceiverBindingDTO;
import org.example.util.enums.ServiceEnum;
import org.springframework.validation.annotation.Validated;

@Validated
public interface Caller2ReceiverBindingService {
    Caller2ReceiverBindingDTO create(@Valid Caller2ReceiverBindingCreateDTO dto);

    String validateTelegramService(@NotBlank(message = "Service link is mandatory.") String serviceLink);

    String validateEmailService(@NotBlank(message = "Service link is mandatory.") String serviceLink);
}
