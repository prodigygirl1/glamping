package ru.promo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.promo.domain.entity.BookingEntity;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @EntityGraph(attributePaths = {"guest", "accommodation", "accommodation.type"})
    List<BookingEntity> findAllByIdOrGuest_phoneNumberOrGuest_email(UUID id, String phoneNumber, String email);
}
