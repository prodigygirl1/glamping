package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.domain.CreateGuest;
import ru.promo.domain.entity.GuestEntity;
import ru.promo.mapper.GuestMapper;
import ru.promo.repository.GuestRepository;
import ru.promo.service.GuestService;

@Service
@Slf4j
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final GuestMapper mapper;

    @Override
    @Transactional
    public GuestEntity saveGuest(CreateGuest createGuest) {
        var guest = mapper.toEntity(createGuest);
        return guestRepository.save(guest);
    }

    @Override
    @Transactional(readOnly = true)
    public GuestEntity findByEmail(String email) {
        return guestRepository.findByEmail(email)
                .orElse(null);
    }
}
