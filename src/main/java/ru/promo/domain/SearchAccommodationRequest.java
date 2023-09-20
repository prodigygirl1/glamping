package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Данные для поиска номеров")
public class SearchAccommodationRequest {
    @Schema(description = "Дата заезда", required = true)
    @NotNull
    private LocalDate dateCheckIn;
    @Schema(description = "Дата выезда", required = true)
    @NotNull
    private LocalDate dateCheckOut;
    @Schema(description = "Количество взрослых", required = true)
    @NotNull
    private Short adultCount;
    @Schema(description = "Количество детей", required = true)
    @NotNull
    private Short childrenCount;
}
