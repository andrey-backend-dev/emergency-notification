package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.dto.request.CallerCreateDTO;
import org.example.dto.request.CallerUpdateDTO;
import org.example.dto.response.CallerResponseDTO;
import org.springframework.validation.annotation.Validated;

import javax.xml.bind.ValidationException;

@Validated
public interface CallerService {
    CallerResponseDTO create(@Valid CallerCreateDTO dto) throws ValidationException;
    CallerResponseDTO findById(@Positive(message = "Id must be more than zero.") long id);
    CallerResponseDTO findByUsername(@NotBlank(message = "Username is mandatory.") String username);
    CallerResponseDTO updateByUsername(@NotBlank(message = "Username is mandatory.") String username, CallerUpdateDTO dto);
    void deleteByUsername(@NotBlank(message = "Username is mandatory.") String username);
}
