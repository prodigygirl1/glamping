package ru.promo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@Table(name = "accommodation_type")
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationTypeEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "start_date", columnDefinition = "DATE", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", columnDefinition = "DATE", nullable = false)
    private LocalDate endDate;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "adult_count", nullable = false)
    private Short adultCount;

    @Column(name = "children_count", nullable = false)
    private Short childrenCount;
}
