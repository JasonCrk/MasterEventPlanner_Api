package com.SAR.ReservationsSAR.context.reservation.domain;

import com.SAR.ReservationsSAR.context.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {
    List<Reservation> findByUserAndStatus(User user, ReservationStatus status);

    Optional<Reservation> findByPaymentId(String paymentId);

    Optional<Reservation> findById(UUID id);

    boolean existsCollisionOfRealizationAndFinishDates(
            UUID establishmentId,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    );

    Reservation save(Reservation reservation);
}
