package ru.promo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "Данные для создания профиля")
@Data
@Builder
public class CreateProfileRequest {
    @Schema(required = true, description = "Фамилия пользователя")
    @NotBlank
    private String surname;

    @Schema(required = true, description = "Имя пользователя")
    @NotBlank
    private String name;

    @Schema(description = "Отчество пользователя")
    private String patronymic;

    @Schema(required = true, description = "Номер телефона пользователя")
    @NotBlank
    private String phoneNumber;

    @Schema(required = true, description = "Почта пользователя")
    @NotBlank
    private String email;

    @Schema(required = true, description = "Пароль")
    @NotBlank
    private String password;
}
