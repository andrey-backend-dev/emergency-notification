package org.example.util.mappers;

import org.example.dto.response.TelegramUserDTO;
import org.example.entity.TelegramUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TelegramUserMapper {

    TelegramUserMapper INSTANCE = Mappers.getMapper(TelegramUserMapper.class);

    TelegramUserDTO userToUserDto(TelegramUser user);
}
