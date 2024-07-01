package org.example.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.CallerCreateDTO;
import org.example.dto.request.CallerUpdateDTO;
import org.example.dto.response.CallerResponseDTO;
import org.example.entity.Caller;
import org.example.entity.Role;
import org.example.repository.CallerRepository;
import org.example.service.CallerService;
import org.example.util.builders.CallerBuilder;
import org.example.util.mappers.CallerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CallerServiceImpl implements CallerService {
    private final CallerRepository repository;
    private final CallerMapper mapper;
    private final PasswordEncoder encoder;
    @Value("${business.username-regex}")
    private String usernameRegex;

    @Override
    @Transactional
    public CallerResponseDTO create(CallerCreateDTO dto) throws ValidationException {

        if (!Pattern.matches(usernameRegex, dto.getUsername()))
            throw new ValidationException("Not valid username");

        Caller caller = new CallerBuilder(dto.getUsername(), encoder.encode(dto.getPassword()), dto.getEmail())
                .message(dto.getMessage())
                .image(dto.getImage())
                .roles(new HashSet<>(Set.of(new Role("ROLE_USER"))))
                .build();

        caller = repository.save(caller);

        return mapper.callerToCallerDto(caller);
    }

    @Override
    @Transactional
    public CallerResponseDTO findById(long id) {
        Caller caller = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("The user with id " + id + " does not exist.")
        );
        return mapper.callerToCallerDto(caller);
    }

    @Override
    @Transactional
    public CallerResponseDTO findByUsername(String username) {
        Caller caller = repository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The user with username " + username + " does not exist.")
        );
        return mapper.callerToCallerDto(caller);
    }

    @Override
    @Transactional
    public CallerResponseDTO updateByUsername(String username, CallerUpdateDTO dto) {
        Caller caller = repository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The user with username " + username + " does not exist.")
        );

        if (dto.getPassword() != null)
            caller.setPassword(encoder.encode(dto.getPassword()));
        if (dto.getEmail() != null)
            caller.setEmail(dto.getEmail());
        if (dto.getMessage() != null)
            caller.setMessage(dto.getMessage());
        if (dto.getImage() != null)
            caller.setImage(dto.getImage());

        return mapper.callerToCallerDto(caller);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        repository.deleteByUsername(username);
    }
}
