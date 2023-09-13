package ru.promo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.promo.domain.PreBookingRequest;
import ru.promo.domain.SearchAccommodationRequest;
import ru.promo.domain.entity.AccommodationEntity;
import ru.promo.domain.entity.BookingEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "booking", description = "API для бронирования номеров")
public interface BookingController {
    @Operation(summary = "Поиск свободных номеров")
    @GetMapping(value = "/api/v1/booking/", consumes = APPLICATION_JSON_VALUE)
    List<AccommodationEntity> findAllAvailable(@RequestBody @NotNull SearchAccommodationRequest request);

    @Operation(summary = "Предварительное бронирование номера")
    @PostMapping(value = "/api/v1/booking/", consumes = APPLICATION_JSON_VALUE)
    void preBook(@RequestBody @NotNull PreBookingRequest request);

    @Operation(summary = "Подтверждение брони предоплатой")
    @PostMapping(value = "/api/v1/booking/pay")
    void payForBooking(@RequestParam("bookingId") @NotNull UUID bookingId);

    @Operation(summary = "Отмена бронирования")
    @PostMapping(value = "/api/v1/booking/cancel")
    void cancelBooking(@RequestParam("bookingId") @NotNull UUID bookingId);

    @Operation(summary = "Поиск брони")
    @GetMapping(value = "/api/v1/booking/search")
    List<BookingEntity> find(@RequestParam(value = "bookingId", required = false) UUID bookingId,
                             @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                             @RequestParam(value = "email", required = false) String email);
}
