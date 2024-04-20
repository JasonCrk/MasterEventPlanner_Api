package com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities.ReservationEntity;
import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities.TopicEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "establishment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EstablishmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "SMALLINT")
    private int maxCapacity;

    @Column(nullable = false)
    private int pricePerHour;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "establishments_topics",
            joinColumns = @JoinColumn(name = "establishment_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<TopicEntity> topics = new ArrayList<>();

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
    private List<EstablishmentImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();

    public static EstablishmentEntity fromDomainModel(Establishment establishment) {
        return EstablishmentEntity.builder()
                .id(establishment.getId())
                .name(establishment.getName())
                .maxCapacity(establishment.getMaxCapacity())
                .description(establishment.getDescription())
                .pricePerHour(establishment.getPricePerHour())
                .topics(establishment.getTopics()
                        .stream()
                        .map(TopicEntity::fromDomainModel)
                        .toList())
                .images(establishment.getImages()
                        .stream()
                        .map(EstablishmentImageEntity::fromDomainModel)
                        .toList())
                .build();
    }

    public Establishment toDomainModel() {
        return Establishment.builder()
                .id(id)
                .name(name)
                .description(description)
                .maxCapacity(maxCapacity)
                .pricePerHour(pricePerHour)
                .topics(topics.stream()
                        .map(TopicEntity::toDomainModel)
                        .toList())
                .images(images.stream()
                        .map(EstablishmentImageEntity::toDomainModel)
                        .toList())
                .build();
    }
}
