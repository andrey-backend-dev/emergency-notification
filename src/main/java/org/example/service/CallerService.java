package org.example.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.dto.request.CallerCreateDto;
import org.example.dto.request.CallerUpdateDto;
import org.example.dto.response.CallerResponseDTO;

public interface CallerService {
    CallerResponseDTO create(@Valid CallerCreateDto dto);
    CallerResponseDTO findById(@Positive long id);
    CallerResponseDTO updateById(@Positive long id, @Valid CallerUpdateDto dto);
    void deleteById(@Positive long id);
}
