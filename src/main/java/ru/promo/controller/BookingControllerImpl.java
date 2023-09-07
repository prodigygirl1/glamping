package ru.promo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.SearchAccommodationRequest;
import ru.promo.domain.entity.AccommodationEntity;
import ru.promo.domain.entity.BookingEntity;
import ru.promo.service.AccommodationService;
import ru.promo.service.BookingService;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookingControllerImpl implements BookingController {
    private final BookingService bookingService;
    private final AccommodationService accommodationService;


    @Override
    public List<AccommodationEntity> findAllAvailable(SearchAccommodationRequest request) {
        return accommodationService.findAllAvailable(request);
    }

    @Override
    public void preBook(PreBookingRequest request) {
        log.debug("Pre-book request: {}", request);
        bookingService.preBook(request);
    }

    @Override
    public void payForBooking(UUID bookingId) {
        log.debug("Booking with id {} payment request", bookingId);
        bookingService.payForBooking(bookingId);
    }

    @Override
    public void cancelBooking(UUID bookingId) {
        log.debug("Booking cancel request with id {}", bookingId);
        bookingService.cancelBooking(bookingId);
    }

    @Override
    public List<BookingEntity> find(UUID bookingId, String phoneNumber, String email) {
        return bookingService.findBooking(bookingId, phoneNumber, email);
    }
}
