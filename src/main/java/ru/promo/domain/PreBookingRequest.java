package ru.promo.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class PreBookingRequest {
    @NotNull
    private UUID accommodationId;

    @NotNull
    private LocalDate dateCheckIn;

    @NotNull
    private LocalDate dateCheckOut;

    @NotNull
    private Short adultCount;

    @NotNull
    private Short childrenCount;

    private String comment;

    @NotBlank
    private String city;

    @NotNull
    private UUID profileId;
}
