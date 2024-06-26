package org.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReceiverCreateDTO {
    @Positive(message = "Id must be more than zero.")
    private long callerId;
    @NotBlank(message = "Receiver name is mandatory.")
    private String name;
    private String email;
    private String telegramUsername;
}