package org.example.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.response.TelegramUserDTO;
import org.example.entity.TelegramUser;
import org.example.repository.TelegramUserRepository;
import org.example.service.TelegramUserService;
import org.example.util.mappers.TelegramUserMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository repository;
    private final TelegramUserMapper mapper;


    @Override
    @Transactional
    public TelegramUserDTO create(long chatId, String username, boolean status) {

        TelegramUser user = repository.findById(chatId).orElse(null);

        if (user != null)
            return mapper.userToUserDto(user);

        TelegramUser entity = new TelegramUser(chatId, username, status);

        entity = repository.save(entity);

        return mapper.userToUserDto(entity);
    }

    @Override
    public TelegramUserDTO create(long chatId, String username) {
        return create(chatId, username, false);
    }

    @Override
    @Transactional
    public TelegramUserDTO changeStatus(long chatId, boolean status) {
        TelegramUser user = repository.findById(chatId).orElseThrow(
                () -> new IllegalArgumentException("The telegram user with chat id " + chatId + " does not exist.")
        );

        user.setStatus(status);

        return mapper.userToUserDto(user);
    }
}
