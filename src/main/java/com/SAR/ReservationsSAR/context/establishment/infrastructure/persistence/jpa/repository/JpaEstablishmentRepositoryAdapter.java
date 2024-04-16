package com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentRepository;
import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaEstablishmentRepositoryAdapter implements EstablishmentRepository {

    @Autowired
    private JpaEstablishmentRepository repository;

    @Override
    public List<Establishment> findAll() {
        return this.repository.findAll().stream()
                .map(EstablishmentEntity::toDomainModel).toList();
    }

    @Override
    public List<Establishment> findAvailableEstablishments(
            LocalDateTime realizationDate,
            LocalDateTime finishDate,
            UUID topicId
    ) {
        return this.repository.findAvailableEstablishments(realizationDate, finishDate, topicId).stream()
                .map(EstablishmentEntity::toDomainModel).toList();
    }

    @Override
    public Optional<Establishment> findById(UUID id) {
        return this.repository.findById(id).map(EstablishmentEntity::toDomainModel);
    }

    @Override
    public boolean hasTopic(UUID establishmentId, UUID topicId) {
        return this.repository.hasTopic(establishmentId, topicId) > 0;
    }
}
