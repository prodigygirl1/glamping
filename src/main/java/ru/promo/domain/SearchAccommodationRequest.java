package ru.promo.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class SearchAccommodationRequest {
    @NotNull
    private LocalDate dateCheckIn;

    @NotNull
    private LocalDate dateCheckOut;

    @NotNull
    private Short adultCount;

    @NotNull
    private Short childrenCount;
}
