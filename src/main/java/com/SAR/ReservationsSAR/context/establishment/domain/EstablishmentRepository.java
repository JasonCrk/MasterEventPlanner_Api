package com.SAR.ReservationsSAR.context.establishment.domain;

import java.util.Optional;
import java.util.UUID;

public interface EstablishmentRepository {
    Optional<Establishment> findById(UUID id);
    boolean hasTopic(UUID establishmentId, UUID topicId);
}
