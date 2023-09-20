package ru.promo.service;

import ru.promo.domain.CreateAccommodationRequest;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.SearchAccommodationRequest;
import ru.promo.domain.entity.AccommodationEntity;

import java.util.List;
import java.util.UUID;

public interface AccommodationService {
    List<AccommodationEntity> findAllAvailable(SearchAccommodationRequest request);

    AccommodationEntity checkAndGet(PreBookingRequest request);

    void createAccommodation(CreateAccommodationRequest request);

    void deleteAccommodation(UUID id);
}
