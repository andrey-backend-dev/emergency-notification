package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.dto.request.CallerCreateDto;
import org.example.dto.request.CallerUpdateDto;
import org.example.dto.response.CallerResponseDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CallerService {
    CallerResponseDTO create(@Valid CallerCreateDto dto);
    CallerResponseDTO findById(@Positive(message = "Id must be more than zero.") long id);
    CallerResponseDTO findByUsername(@NotBlank(message = "Username is mandatory.") String username);
    CallerResponseDTO updateByUsername(@NotBlank(message = "Username is mandatory.") String username, CallerUpdateDto dto);
    void deleteByUsername(@NotBlank(message = "Username is mandatory.") String username);
}
