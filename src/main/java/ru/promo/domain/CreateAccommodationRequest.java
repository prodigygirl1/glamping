package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Данные для добавления номера")
@Data
@Builder
public class CreateAccommodationRequest {
    @Schema(required = true, description = "Название номера")
    @NotBlank
    private String name;

    @Schema(required = true, description = "Id типа номера")
    @NotNull
    private UUID accommodationTypeId;
}
