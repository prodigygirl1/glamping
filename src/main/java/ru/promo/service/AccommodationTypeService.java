package ru.promo.service;

import ru.promo.domain.entity.AccommodationTypeEntity;

import java.util.List;
import java.util.UUID;

public interface AccommodationTypeService {
    AccommodationTypeEntity findById(UUID id);

    List<AccommodationTypeEntity> findAll();
}
