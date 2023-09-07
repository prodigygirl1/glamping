package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.entity.BookingEntity;
import ru.promo.mapper.GuestMapper;
import ru.promo.repository.BookingRepository;
import ru.promo.service.AccommodationService;
import ru.promo.service.BookingService;
import ru.promo.service.GuestService;

import java.util.List;
import java.util.UUID;

import static ru.promo.util.exception.BadRequestException.invalidMessage;
import static ru.promo.util.exception.ResourceNotFoundException.notFound;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    private final AccommodationService accommodationService;
    private final GuestService guestService;
    private final GuestMapper guestMapper;

    @Override
    public void preBook(PreBookingRequest request) {
        var accommodation = accommodationService.checkAndGet(request);
        if (accommodation == null) {
            throw invalidMessage("Номер уже забронирован или не соответствует заданным условиям");
        }

        var guest = guestService.findByEmail(request.getEmail());
        if (guest != null) {
            if (!guest.getSurname().equals(request.getSurname()) || !guest.getName().equals(request.getName())) {
                throw invalidMessage("Указанный email уже используется");
            }
        } else {
            var guestInfo = guestMapper.toCreateDto(request);
            guest = guestService.saveGuest(guestInfo);
        }

        var booking = BookingEntity.builder()
                .accommodation(accommodation)
                .city(request.getCity())
                .comment(request.getComment())
                .dateCheckIn(request.getDateCheckIn())
                .dateCheckOut(request.getDateCheckOut())
                .adultCount(request.getAdultCount())
                .childrenCount(request.getChildrenCount())
                .guest(guest)
                .isActive(true)
                .isPaid(false)
                .build();
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void payForBooking(UUID bookingId) {
        var booking = findById(bookingId);
        if (!booking.isActive()) {
            throw invalidMessage("Бронирование с id %s неактивно", bookingId);
        }
        if (!booking.isPaid()) {
            booking.setPaid(true);
            bookingRepository.save(booking);
        } else {
            throw invalidMessage("Бронирование с id %s уже оплачено", bookingId);
        }
    }

    @Override
    @Transactional
    public void cancelBooking(UUID bookingId) {
        var booking = findById(bookingId);
        if (booking.isActive()) {
            booking.setActive(false);
            bookingRepository.save(booking);
        } else {
            throw invalidMessage("Бронирование с id %s уже отменено", bookingId);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingEntity> findBooking(UUID bookingId, String phoneNumber, String email) {
        return bookingRepository.findAllByIdOrGuest_phoneNumberOrGuest_email(bookingId, phoneNumber, email);
    }

    public BookingEntity findById(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> notFound("Бронирование с id %s не найдено", id));
    }
}
