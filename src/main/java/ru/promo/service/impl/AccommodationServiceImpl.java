package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.SearchAccommodationRequest;
import ru.promo.domain.entity.AccommodationEntity;
import ru.promo.repository.AccommodationRepository;
import ru.promo.service.AccommodationService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static ru.promo.util.exception.BadRequestException.invalidMessage;
import static ru.promo.util.exception.ResourceNotFoundException.notFound;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    @Override
    public List<AccommodationEntity> findAllAvailable(SearchAccommodationRequest request) {
        var start_date_booking = request.getDateCheckIn();
        var end_date_booking = request.getDateCheckOut();
        if (start_date_booking.isAfter(end_date_booking)) {
            throw invalidMessage("Дата начала должна быть раньше даты окончания поиска");
        }
        return accommodationRepository.findAllAvailable(start_date_booking, end_date_booking,
                        request.getAdultCount(), request.getChildrenCount())
                .stream()
                .toList();
    }

    @Transactional
    public AccommodationEntity findById(UUID id) {
        return accommodationRepository.findById(id)
                .orElseThrow(() -> notFound("Номер с id %s не найден", id));
    }

    @Override
    @Transactional
    public AccommodationEntity checkAndGet(PreBookingRequest request) {
        var accommodation = findById(request.getAccommodationId());
        var searchRequest = SearchAccommodationRequest
                .builder()
                .dateCheckIn(request.getDateCheckIn())
                .dateCheckOut(request.getDateCheckOut())
                .adultCount(request.getAdultCount())
                .childrenCount(request.getChildrenCount())
                .build();
        if (findAllAvailable(searchRequest).contains(accommodation)) {
            return accommodation;
        }
//        var type = accommodation.getType();
//
//        var peopleCondition = type.getChildrenCount() >= request.getChildrenCount()
//                && type.getAdultCount() >= request.getAdultCount();
//        var dateCondition = dateIsCorrect(type.getStartDate(), type.getEndDate(),
//                request.getDateCheckIn(), request.getDateCheckOut());
//
//        if (peopleCondition && dateCondition) {
//            return accommodation;
//        }
        return null;
    }

    private boolean dateIsCorrect(LocalDate start_date_accommodation, LocalDate end_date_accommodation,
                                  LocalDate start_date_booking, LocalDate end_date_booking) {

        var durationYears = start_date_booking.minusYears(start_date_accommodation.getYear());

        start_date_accommodation = start_date_accommodation.plusYears(durationYears.getYear());
        end_date_accommodation = end_date_accommodation.plusYears(durationYears.getYear());

        return (start_date_accommodation.isBefore(start_date_booking) || start_date_accommodation.equals(start_date_booking))
                && (end_date_booking.isBefore(end_date_accommodation)) || end_date_booking.equals(end_date_accommodation);
    }
}
