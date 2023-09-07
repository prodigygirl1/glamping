package ru.promo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.domain.entity.GuestEntity;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<GuestEntity, UUID> {
    Optional<GuestEntity> findByEmail(String email);
}
