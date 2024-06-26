package org.example.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.dto.response.TelegramUserDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TelegramUserService {
    TelegramUserDTO create(@Positive(message = "Chat id must be positive.") long chatId,
                           @NotBlank(message = "Username is mandatory.") String username,
                           boolean status);

    TelegramUserDTO create(@Positive(message = "Chat id must be positive.") long chatId,
                           @NotBlank(message = "Username is mandatory.") String username);

    TelegramUserDTO changeStatus(@Positive(message = "Chat id must be positive.") long chatId,
                                 boolean status);
}
