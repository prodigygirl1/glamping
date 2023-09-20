package ru.promo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.domain.CreateAccommodationRequest;
import ru.promo.domain.entity.AccommodationTypeEntity;
import ru.promo.service.AccommodationService;
import ru.promo.service.AccommodationTypeService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminControllerImpl implements AdminController {
    private final AccommodationService accommodationService;
    private final AccommodationTypeService accommodationTypeService;

    @Override
    public void createAccommodation(CreateAccommodationRequest request) {
        accommodationService.createAccommodation(request);
    }

    @Override
    public void deleteAccommodation(UUID id) {
        accommodationService.deleteAccommodation(id);
    }

    @Override
    public List<AccommodationTypeEntity> getAccommodationTypes() {
        return accommodationTypeService.findAll();
    }
}
