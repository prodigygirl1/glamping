package ru.promo.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateGuest {
    @NotBlank
    private String surname;

    @NotBlank
    private String name;

    private String patronymic;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;
}
