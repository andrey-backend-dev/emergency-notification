package org.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CallerCreateDto {
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Email is mandatory")
    private String email;
    private String message;
    private String image;
}
