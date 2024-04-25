package com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.reservation.domain.ReservationStatus;
import com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities.ReservationEntity;
import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaReservationRepository extends JpaRepository<ReservationEntity, UUID> {
    List<ReservationEntity> findByCoordinatorAndStatus(UserEntity user, ReservationStatus status);

    Optional<ReservationEntity> findByPayId(String payId);

    @Query(
            nativeQuery = true,
            value = "SELECT CASE WHEN COUNT(r.id) > 0 THEN 1 ELSE 0 END FROM reservation r " +
            "WHERE r.establishment_id = :establishmentId " +
            "AND r.status = 'PENDING' " +
            "AND (" +
                "(:realizationDate <= r.realization_date AND r.realization_date < DATE_ADD(:finishDate, INTERVAL 2 HOUR)) " +
                "OR (:realizationDate <= DATE_ADD(r.finish_date, INTERVAL 2 HOUR) AND DATE_ADD(r.finish_date, INTERVAL 2 HOUR) <= DATE_ADD(:finishDate, INTERVAL 2 HOUR)) " +
                "OR (r.realization_date <= :realizationDate AND DATE_ADD(:finishDate, INTERVAL 2 HOUR) <= DATE_ADD(r.finish_date, INTERVAL 2 HOUR)) " +
            ")")
    int existsBetweenRealizationDateAndFinishDate(
            @Param("establishmentId") UUID establishmentId,
            @Param("realizationDate") LocalDateTime realizationDate,
            @Param("finishDate") LocalDateTime finishDate
    );
}
