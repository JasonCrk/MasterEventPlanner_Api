package com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.establishment.infrastructure.persistence.jpa.entities.EstablishmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
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

    @Query(
            nativeQuery = true,
            value = """
                    SELECT DISTINCT e.* FROM establishment e
                    LEFT JOIN reservation r ON e.id = r.establishment_id
                    LEFT JOIN establishments_topics et ON e.id = et.establishment_id
                    WHERE
                        (:topicId IS NULL OR et.topic_id = :topicId) AND
                        CASE WHEN r.establishment_id IS NOT NULL
                            THEN r.status = 'PENDING' AND
                                NOT (
                                    (:realizationDate <= r.realization_date
                                        AND r.realization_date < DATE_ADD(:finishDate, INTERVAL 2 HOUR))
                                    OR (:realizationDate <= DATE_ADD(r.finish_date, INTERVAL 2 HOUR)
                                        AND DATE_ADD(r.finish_date, INTERVAL 2 HOUR) <= DATE_ADD(:finishDate, INTERVAL 2 HOUR))
                                    OR (r.realization_date <= :realizationDate
                                        AND DATE_ADD(:finishDate, INTERVAL 2 HOUR) <= DATE_ADD(r.finish_date, INTERVAL 2 HOUR))
                                ) ELSE TRUE
                        END
                    """
    )
    List<EstablishmentEntity> findAvailableEstablishments(
            @Param("realizationDate") LocalDateTime realizationDate,
            @Param("finishDate") LocalDateTime finishDate,
            @Param("topicId") UUID topicId
    );
}
