package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Schema(description = "Данные для создания предварительного бронирования")
public class PreBookingRequest {
    @Schema(description = "Id номера", required = true)
    @NotNull
    private UUID accommodationId;
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
    @Schema(description = "Комментарий")
    private String comment;
    @Schema(description = "Город", required = true)
    @NotBlank
    private String city;
    @Schema(description = "Id пользователя", required = true)
    @NotNull
    private UUID profileId;
}
