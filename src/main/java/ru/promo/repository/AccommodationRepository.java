package ru.promo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.promo.domain.entity.AccommodationEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<AccommodationEntity, UUID> {
    @Query(value = """
            SELECT * FROM accommodation acc
            LEFT JOIN accommodation_type acc_type on acc.accommodation_type_id=acc_type.id
            WHERE acc_type.adult_count >= :adult_count AND acc_type.children_count >= :children_count\s
            AND EXTRACT(month FROM acc_type.start_date) <= EXTRACT(month FROM CAST(:start_date AS DATE))\s
            AND EXTRACT(day FROM acc_type.start_date) <= EXTRACT(day FROM CAST(:start_date AS DATE))
            AND EXTRACT(month FROM CAST(:end_date AS DATE)) <= EXTRACT(month FROM acc_type.end_date)\s
            AND EXTRACT(day FROM CAST(:end_date AS DATE)) <= EXTRACT(day FROM acc_type.end_date)
            AND acc.is_active=true
            AND acc.id NOT IN
             (SELECT accommodation.id FROM booking
             LEFT JOIN accommodation on booking.accommodation_id = accommodation.id
             WHERE booking.date_check_in >= :start_date\s
             AND booking.date_check_out <= :end_date
             AND booking.is_active = true);
            """,
            nativeQuery = true)
    List<AccommodationEntity> findAllAvailable(LocalDate start_date, LocalDate end_date,
                                               Short adult_count, Short children_count);
}
