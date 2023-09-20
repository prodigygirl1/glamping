package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.promo.domain.entity.AccommodationTypeEntity;
import ru.promo.repository.AccommodationTypeRepository;
import ru.promo.service.AccommodationTypeService;

import java.util.List;
import java.util.UUID;

import static ru.promo.util.exception.ResourceNotFoundException.notFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationTypeServiceImpl implements AccommodationTypeService {
    private final AccommodationTypeRepository accommodationTypeRepository;

    @Override
    public AccommodationTypeEntity findById(UUID id) {
        return accommodationTypeRepository.findById(id)
                .orElseThrow(() -> notFound("Тип номера с id %s не найден", id));
    }

    @Override
    public List<AccommodationTypeEntity> findAll() {
        return accommodationTypeRepository.findAll();
    }
}
