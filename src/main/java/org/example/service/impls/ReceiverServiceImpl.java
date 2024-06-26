package org.example.service.impls;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.ReceiverCreateDTO;
import org.example.dto.request.ReceiverUpdateDTO;
import org.example.dto.response.ReceiverDTO;
import org.example.entity.Caller;
import org.example.entity.Receiver;
import org.example.entity.TelegramUser;
import org.example.repository.ReceiverRepository;
import org.example.repository.CallerRepository;
import org.example.repository.TelegramUserRepository;
import org.example.service.ReceiverService;
import org.example.util.mappers.ReceiverMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {
    private final ReceiverRepository repository;
    private final CallerRepository callerRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final ReceiverMapper mapper;
    @Value(value = "${business.telegram-username-regex}")
    private String telegramRegex;
    @Value(value = "${business.email-regex}")
    private String emailRegex;

    @Override
    @Transactional
    public ReceiverDTO create(ReceiverCreateDTO dto) {

        String email = validateEmailFormat(dto.getEmail());
        String telegramUsername = validateTelegramUsername(dto.getTelegramUsername());

        if (email == null && telegramUsername == null) {
            throw new IllegalArgumentException("To create binding, you need to specify person email or/and telegram username.");
        } else {
            Caller caller = callerRepository.findById(dto.getCallerId()).orElseThrow(
                    () -> new IllegalArgumentException("The user with id " + dto.getCallerId() + " does not exist.")
            );

            TelegramUser tgUser = null;

            if (telegramUsername != null) {
                tgUser = telegramUserRepository.findByUsername(telegramUsername).orElseThrow(
                        () -> new IllegalArgumentException("The telegram user with username " + telegramUsername + " does not exist.")
                );
            }

            Receiver entity = new Receiver(
                    caller,
                    dto.getName(),
                    email,
                    tgUser);

            entity = repository.save(entity);

            return mapper.receiverToReceiverDto(entity);
        }
    }

    @Override
    @Transactional
    public ReceiverDTO updateById(long id, ReceiverUpdateDTO dto) throws AccessDeniedException {

        Receiver receiver = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("The receiver with id " + id + " does not exist.")
        );

        if (receiver.getCaller().getId() != dto.getCallerId()) {
            throw new AccessDeniedException("You are not allowed to update not yours receivers.");
        }

        String tgUsername = validateTelegramUsername(dto.getTelegramUsername());
        if (tgUsername != null) {
            receiver.setTelegramUser(
                    telegramUserRepository.findByUsername(tgUsername).orElseThrow(
                            () -> new IllegalArgumentException("The telegram user with username " + tgUsername + " does not exist.")
                    )
            );
        }

        String name = dto.getName();
        if (name != null && !name.isBlank()) {
            receiver.setName(name);
        }

        String email = validateEmailFormat(dto.getEmail());
        if (email != null && !email.isBlank()) {
            receiver.setEmail(email);
        }

        return mapper.receiverToReceiverDto(receiver);
    }

    @Override
    public String validateTelegramUsername(String telegramUsername) {
        if (telegramUsername == null)
            return null;
        if (Pattern.matches(telegramRegex, telegramUsername))
            return telegramUsername.substring(1);
        throw new ValidationException("Telegram username is invalid");
    }

    @Override
    public String validateEmailFormat(String email) {
        if (email == null)
            return null;
        if (Pattern.matches(emailRegex, email))
            return email;
        throw new ValidationException("Email is invalid");
    }
}
