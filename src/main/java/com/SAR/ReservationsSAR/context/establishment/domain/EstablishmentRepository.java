package com.SAR.ReservationsSAR.context.establishment.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstablishmentRepository {
    List<Establishment> findAll();

    List<Establishment> findAvailableEstablishments(
            LocalDateTime realizationDate,
            LocalDateTime finishDate,
            UUID topicId
    );

    Optional<Establishment> findById(UUID id);

    boolean hasTopic(UUID establishmentId, UUID topicId);
}
