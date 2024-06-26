package org.example.util.mappers;

import org.example.dto.response.ReceiverDTO;
import org.example.entity.Receiver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ReceiverMapper {

    public ReceiverDTO receiverToReceiverDto(Receiver receiver) {
        ReceiverDTO dto = new ReceiverDTO();
        dto.setName(receiver.getName());
        dto.setEmail(receiver.getEmail());
        dto.setCallerId(receiver.getCaller().getId());
        if (receiver.getTelegramUser() != null)
            dto.setTelegramUsername(receiver.getTelegramUser().getUsername());
        return dto;
    }
}
