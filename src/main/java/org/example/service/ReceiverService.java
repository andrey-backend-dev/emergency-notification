package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.dto.request.ReceiverCreateDTO;
import org.example.dto.request.ReceiverUpdateDTO;
import org.example.dto.response.ReceiverDTO;
import org.springframework.validation.annotation.Validated;

import java.nio.file.AccessDeniedException;

@Validated
public interface ReceiverService {
    ReceiverDTO create(@Valid ReceiverCreateDTO dto);

    ReceiverDTO updateById(@Positive(message = "Id must be more than zero.") long id, @Valid ReceiverUpdateDTO dto) throws AccessDeniedException;

    String validateTelegramUsername(@NotBlank(message = "Service link is mandatory.") String telegramUsername);

    String validateEmailFormat(@NotBlank(message = "Service link is mandatory.") String email);
}
