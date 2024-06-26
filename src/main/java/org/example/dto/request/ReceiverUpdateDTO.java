package org.example.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ReceiverUpdateDTO {
    @Positive(message = "Id must be more than zero.")
    private long callerId;
    private String name;
    private String email;
    private String telegramUsername;
}
