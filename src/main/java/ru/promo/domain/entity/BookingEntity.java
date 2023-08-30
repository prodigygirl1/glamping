package ru.promo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "date_check_in", columnDefinition = "DATE", nullable = false)
    private Date dateCheckIn;

    @Column(name = "date_check_out", columnDefinition = "DATE", nullable = false)
    private Date dateCheckOut;

    @Column(name = "comment")
    private String comment;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private GuestEntity guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", referencedColumnName = "id")
    private AccommodationEntity accommodation;
}
