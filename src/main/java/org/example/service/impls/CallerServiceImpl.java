package org.example.service.impls;

import jakarta.transaction.Transactional;
import org.example.dto.request.CallerCreateDto;
import org.example.dto.request.CallerUpdateDto;
import org.example.dto.response.CallerResponseDTO;
import org.example.entity.Caller;
import org.example.repository.CallerRepository;
import org.example.service.CallerService;
import org.example.util.builders.CallerBuilder;
import org.example.util.mappers.CallerMapper;
import org.springframework.stereotype.Service;

@Service
public class CallerServiceImpl implements CallerService {
    private final CallerRepository repository;
    private final CallerMapper mapper;

    public CallerServiceImpl(CallerRepository repository, CallerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CallerResponseDTO create(CallerCreateDto dto) {
        Caller caller = new CallerBuilder(dto.getUsername(), dto.getPassword(), dto.getEmail())
                .message(dto.getMessage())
                .image(dto.getImage())
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
    public CallerResponseDTO updateByUsername(String username, CallerUpdateDto dto) {
        Caller caller = repository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The user with username " + username + " does not exist.")
        );

        if (dto.getPassword() != null)
            caller.setPassword(dto.getPassword());
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
