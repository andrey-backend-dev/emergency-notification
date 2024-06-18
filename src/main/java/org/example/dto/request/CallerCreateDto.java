package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CallerCreateDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
    private String message;
    private String image;
}
