package org.example.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.dto.request.CallerCreateDTO;
import org.example.dto.request.LoginDTO;
import org.example.dto.response.CallerResponseDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import javax.xml.bind.ValidationException;
import java.util.Collection;

@Validated
public interface AuthenticationService {

    CallerResponseDTO register(@Valid CallerCreateDTO dto, HttpServletRequest request, HttpServletResponse response) throws ValidationException;

    CallerResponseDTO login(@Valid LoginDTO dto, HttpServletRequest request, HttpServletResponse response);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void authenticate(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
