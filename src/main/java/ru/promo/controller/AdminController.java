package ru.promo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.promo.domain.CreateAccommodationRequest;
import ru.promo.domain.entity.AccommodationTypeEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "admin", description = "API для админки")
public interface AdminController {
    @Operation(summary = "Добавление номера")
    @PostMapping(value = "/api/v1/admin/accommodation", consumes = APPLICATION_JSON_VALUE)
    void createAccommodation(@RequestBody @NotNull CreateAccommodationRequest request);

    @Operation(summary = "Удаление номера")
    @DeleteMapping(value = "/api/v1/admin/accommodation/{id}")
    void deleteAccommodation(@PathVariable UUID id);

    @Operation(summary = "Получение списка типов номеров")
    @GetMapping(value = "/api/v1/admin/accommodation/type")
    List<AccommodationTypeEntity> getAccommodationTypes();
}
