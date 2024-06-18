package org.example.util.mappers;

import org.example.dto.response.CallerResponseDTO;
import org.example.entity.Caller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CallerMapper {

    CallerMapper INSTANCE = Mappers.getMapper(CallerMapper.class);

    CallerResponseDTO callerToCallerDto(Caller caller);
}
