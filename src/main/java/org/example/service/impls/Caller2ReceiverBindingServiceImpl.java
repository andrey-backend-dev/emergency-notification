package org.example.service.impls;

import jakarta.validation.ValidationException;
import org.example.dto.request.Caller2ReceiverBindingCreateDTO;
import org.example.dto.response.Caller2ReceiverBindingDTO;
import org.example.entity.Caller;
import org.example.entity.Caller2ReceiverBinding;
import org.example.repository.Caller2ReceiverBindingRepository;
import org.example.repository.CallerRepository;
import org.example.service.Caller2ReceiverBindingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class Caller2ReceiverBindingServiceImpl implements Caller2ReceiverBindingService {
    private final Caller2ReceiverBindingRepository repository;
    private final CallerRepository callerRepository;
    @Value(value = "${business.telegram-username-regex}")
    private String telegramRegex;
    @Value(value = "${business.email-regex}")
    private String emailRegex;

    Caller2ReceiverBindingServiceImpl(Caller2ReceiverBindingRepository repository, CallerRepository callerRepository) {
        this.repository = repository;
        this.callerRepository = callerRepository;
    }

    @Override
    public Caller2ReceiverBindingDTO create(Caller2ReceiverBindingCreateDTO dto) {
        String serviceLink = switch (dto.getService()) {
            case TELEGRAM -> validateTelegramService(dto.getServiceLink());
            case EMAIL -> validateEmailService(dto.getServiceLink());
        };

        Caller caller = callerRepository.findById(dto.getCallerId()).orElseThrow(
                () -> new IllegalArgumentException("The user with id " + dto.getCallerId() + " does not exist.")
        );

        Caller2ReceiverBinding entity = new Caller2ReceiverBinding(caller, dto.getReceiver(), dto.getService(), serviceLink);

        entity = repository.save(entity);

        return new Caller2ReceiverBindingDTO(entity);
    }

    @Override
    public String validateTelegramService(String serviceLink) {
        if (Pattern.matches(telegramRegex, serviceLink))
            return "https://t.me/" + serviceLink.substring(1);
        throw new ValidationException("Telegram username is invalid");
    }

    @Override
    public String validateEmailService(String serviceLink) {
        if (Pattern.matches(emailRegex, serviceLink))
            return serviceLink;
        throw new ValidationException("Email is invalid");
    }
}
