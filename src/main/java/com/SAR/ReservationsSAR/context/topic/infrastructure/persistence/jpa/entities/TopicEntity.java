package com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;
import com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities.ReservationEntity;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "topic")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "topics", cascade = CascadeType.ALL)
    private List<EstablishmentEntity> establishments = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<ReservationEntity> reservations = new ArrayList<>();

    public static TopicEntity fromDomainModel(Topic topic) {
        return TopicEntity.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }

    public Topic toDomainModel() {
        return new Topic(id, name);
    }
}
