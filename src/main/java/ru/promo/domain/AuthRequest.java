package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Данные для авторизации")
public record AuthRequest(@Schema(required = true, description = "Логин (почта)") @NotBlank
                          String username,
                          @Schema(required = true, description = "Пароль") @NotBlank
                          String password) {
}
