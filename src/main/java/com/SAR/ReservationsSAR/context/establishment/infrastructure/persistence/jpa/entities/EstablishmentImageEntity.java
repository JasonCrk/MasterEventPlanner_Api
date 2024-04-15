package com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentImage;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "establishment_image")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EstablishmentImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "establishment_id", nullable = false)
    private EstablishmentEntity establishment;

    public static EstablishmentImageEntity fromDomainModel(EstablishmentImage image) {
        return EstablishmentImageEntity.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .build();
    }

    public EstablishmentImage toDomainModel() {
        return new EstablishmentImage(id, imageUrl);
    }
}
