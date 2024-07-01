package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.dto.request.ReceiverCreateDTO;
import org.example.dto.request.ReceiverUpdateDTO;
import org.example.dto.response.ReceiverDTO;
import org.springframework.validation.annotation.Validated;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Validated
public interface ReceiverService {
    ReceiverDTO create(@NotBlank(message = "Username is mandatory.") String username, @Valid ReceiverCreateDTO dto);

    ReceiverDTO updateByUsername(@NotBlank(message = "Name is mandatory.") String username, @Valid ReceiverUpdateDTO dto) throws AccessDeniedException;

    String validateTelegramUsername(@NotBlank(message = "Telegram username is mandatory.") String telegramUsername);

    String validateEmailFormat(@NotBlank(message = "Email is mandatory.") String email);

    List<ReceiverDTO> findAllByCallerUsername(@NotBlank(message = "Username is mandatory.") String username);
}
