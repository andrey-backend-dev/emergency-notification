package org.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.util.enums.ServiceEnum;

@AllArgsConstructor
@Getter
public class Caller2ReceiverBindingCreateDTO {
    @Positive(message = "Id must be more than zero.")
    private long callerId;
    @NotBlank(message = "Receiver name is mandatory.")
    private String receiver;
    @NotNull(message = "Service is mandatory.")
    private ServiceEnum service;
    @NotBlank(message = "Service link is mandatory.")
    private String serviceLink;
}