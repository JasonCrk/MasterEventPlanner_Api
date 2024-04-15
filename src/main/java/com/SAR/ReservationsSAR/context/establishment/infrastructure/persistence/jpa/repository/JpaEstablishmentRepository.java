package com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaEstablishmentRepository extends JpaRepository<EstablishmentEntity, UUID> {
    @Query(
            nativeQuery = true,
            value = """
            SELECT CASE WHEN COUNT(et.establishment_id) > 0 THEN 1 ELSE 0 END FROM establishments_topics et\s
            WHERE et.establishment_id = :establishmentId AND et.topic_id = :topicId
            """)
    int hasTopic(@Param("establishmentId") UUID establishmentId, @Param("topicId") UUID topicId);
}
