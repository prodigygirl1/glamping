package ru.promo.service;

import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.entity.BookingEntity;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    void preBook(PreBookingRequest preBookingRequest);

    void payForBooking(UUID bookingId);

    void cancelBooking(UUID bookingId);

    List<BookingEntity> findBooking(UUID bookingId, String phoneNumber, String email);
}
