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

import java.util.List;
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
    public ReceiverDTO create(String username, ReceiverCreateDTO dto) {

        String email = validateEmailFormat(dto.getEmail());
        String telegramUsername = validateTelegramUsername(dto.getTelegramUsername());

        Caller caller = callerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The user with username " + username + " does not exist.")
        );
        TelegramUser user = null;
        if (telegramUsername != null) {
            user = telegramUserRepository.findByUsername(telegramUsername).orElseThrow(
                    () -> new IllegalArgumentException("The telegram user with username " + telegramUsername + " does not exist.")
            );
        }

        checkIfReceiverDoesNotExist(dto.getName(), email, user == null ? null : user.getChatId(), caller.getId());

        Receiver entity = new Receiver(
                caller,
                dto.getName(),
                email,
                user);

        entity = repository.save(entity);

        return mapper.receiverToReceiverDto(entity);
    }

    @Override
    public List<ReceiverDTO> findAllByCallerUsername(String username) {
        Caller caller = callerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The caller with username " + username + " does not exist.")
        );

        return repository.findByCallerId(caller.getId()).stream().map(mapper::receiverToReceiverDto).toList();
    }

    @Override
    @Transactional
    public ReceiverDTO updateByUsername(String username, ReceiverUpdateDTO dto) {

        Caller caller = callerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("The caller with username " + username + " does not exist.")
        );

        Receiver receiver = repository.findOneByCallerIdAndName(caller.getId(), dto.getName()).orElseThrow(
                () -> new IllegalArgumentException("No such receiver found with caller id " + caller.getId() + " and receiver name " + dto.getName() + ".")
        );

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

    private void checkIfReceiverDoesNotExist(String name, String email, Long tgId, long callerId) {
        if (email != null) {
            if (tgId != null) {
                if (repository.findCallersReceiverWithEmailAndTelegram(name, email, tgId, callerId).isPresent())
                    throw new IllegalArgumentException("Please, specify receiver with unique name/email/telegram params.");
            } else {
                if (repository.findCallersReceiverWithEmail(name, email, callerId).isPresent())
                    throw new IllegalArgumentException("Please, specify receiver with unique name/email params.");
            }
        } else if (tgId != null) {
            if (repository.findCallersReceiverWithTelegram(name, tgId, callerId).isPresent()) {
                throw new IllegalArgumentException("Please, specify receiver with unique name/telegram params.");
            }
        } else {
            throw new IllegalArgumentException("To create binding, you need to specify person email or/and telegram username.");
        }
    }
}
