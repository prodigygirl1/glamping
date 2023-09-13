package ru.promo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.domain.entity.ProfileEntity;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID> {
    Optional<ProfileEntity> findByEmail(String email);
}
