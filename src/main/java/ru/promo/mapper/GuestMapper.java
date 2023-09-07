package ru.promo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.promo.domain.CreateGuest;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.entity.GuestEntity;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestEntity toEntity(CreateGuest guest);

    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "patronymic", target = "patronymic")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "email", target = "email")
    CreateGuest toCreateDto(PreBookingRequest request);
}
