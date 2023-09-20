package ru.promo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.promo.domain.AccessResponse;
import ru.promo.domain.AuthRequest;
import ru.promo.domain.CreateProfileRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "profile", description = "API для получения данных profile")
public interface ProfileController {
    @Operation(summary = "Создание аккаунта пользователя")
    @PostMapping(value = "/api/v1/profile/", consumes = APPLICATION_JSON_VALUE)
    void createProfile(@Parameter(description = "Данные аккаунта пользователя", required = true)
                       @RequestBody @NotNull @Valid CreateProfileRequest request);

    @Operation(summary = "Авторизация пользователя")
    @PostMapping(value = "/api/v1/profile/login", consumes = APPLICATION_JSON_VALUE)
    AccessResponse auth(@Parameter(description = "Данные для авторизации", required = true)
                        @RequestBody @NotNull @Valid AuthRequest request);
}
