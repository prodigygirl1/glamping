package ru.promo.service;

import ru.promo.domain.CreateGuest;
import ru.promo.domain.entity.GuestEntity;

public interface GuestService {
    GuestEntity saveGuest(CreateGuest createGuest);

    GuestEntity findByEmail(String email);
}
