package com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;
import com.SAR.ReservationsSAR.context.reservation.domain.Reservation;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationStatus;
import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities.TopicEntity;
import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "coordinator_id", nullable = false)
    private UserEntity coordinator;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private EstablishmentEntity establishment;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;

    @Builder.Default
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime realizationDate;

    @Column(nullable = false)
    private LocalDateTime finishDate;

    @Column(nullable = false)
    private String payId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT 'PENDING'")
    private ReservationStatus status = ReservationStatus.PENDING;

    public static ReservationEntity fromDomainModel(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId())
                .coordinator(UserEntity.fromDomainModel(reservation.getCoordinator()))
                .establishment(EstablishmentEntity.fromDomainModel(reservation.getEstablishment()))
                .topic(TopicEntity.fromDomainModel(reservation.getTopic()))
                .createdAt(reservation.getCreatedAt())
                .status(reservation.getStatus())
                .realizationDate(reservation.getRealizationDate())
                .finishDate(reservation.getFinishDate())
                .payId(reservation.getPayId())
                .build();
    }

    public Reservation toDomainModel() {
        return Reservation.builder()
                .id(id)
                .coordinator(coordinator.toDomainModel())
                .establishment(establishment.toDomainModel())
                .topic(topic.toDomainModel())
                .status(status)
                .createdAt(createdAt)
                .finishDate(finishDate)
                .realizationDate(realizationDate)
                .payId(payId)
                .build();
    }
}
