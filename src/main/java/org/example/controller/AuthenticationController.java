package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.CallerCreateDTO;
import org.example.dto.request.LoginDTO;
import org.example.dto.response.CallerResponseDTO;
import org.example.service.AuthenticationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/authentication/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "register/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO register(@RequestBody CallerCreateDTO dto, HttpServletRequest request, HttpServletResponse response) throws ValidationException {
        return authenticationService.register(dto, request, response);
    }

    @PostMapping(value = "login/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CallerResponseDTO login(@RequestBody LoginDTO dto, HttpServletRequest request, HttpServletResponse response) {
        return authenticationService.login(dto, request, response);
    }

    @GetMapping(value = "logout/")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.logout(request, response);
    }
}
