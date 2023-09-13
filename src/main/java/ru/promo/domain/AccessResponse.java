package ru.promo.domain;

import java.util.UUID;

public record AccessResponse(String token, UUID profile_id) {
}
