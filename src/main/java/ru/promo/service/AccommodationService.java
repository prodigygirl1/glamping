package ru.promo.service;

import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.SearchAccommodationRequest;
import ru.promo.domain.entity.AccommodationEntity;

import java.util.List;

public interface AccommodationService {
    List<AccommodationEntity> findAllAvailable(SearchAccommodationRequest request);

    AccommodationEntity checkAndGet(PreBookingRequest request);
}
