package ru.promo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.promo.domain.entity.BookingEntity;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @EntityGraph(attributePaths = {"guest", "accommodation", "accommodation.type", "guest.role"})
    List<BookingEntity> findAllByIdOrGuest_phoneNumberOrGuest_email(UUID id, String phoneNumber, String email);

    @Modifying
    @Query(value = "UPDATE BookingEntity b SET b.isActive=false " +
            "WHERE b.isActive=true AND b.isPaid=false AND (b.createdAt + '12 hours' <= CURRENT_TIMESTAMP)")
    int cancelOlderThanTimeBookings();
}
