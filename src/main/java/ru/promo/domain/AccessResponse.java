package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Данные для доступа")
public record AccessResponse(
        @Schema(description = "Токен")
        String token,
        @Schema(description = "Id профиля")
        UUID profile_id) {
}
