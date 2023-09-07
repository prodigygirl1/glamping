package ru.promo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.domain.entity.AccommodationTypeEntity;

import java.util.UUID;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationTypeEntity, UUID> {
}
