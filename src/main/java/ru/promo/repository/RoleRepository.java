package ru.promo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.domain.entity.RoleEntity;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(String name);
}
