package com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.reservation.domain.Reservation;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationRepository;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationStatus;
import com.SAR.ReservationsSAR.context.reservation.infrastructure.persistence.jpa.entities.ReservationEntity;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaReservationsRepositoryAdapter implements ReservationRepository {

    @Autowired
    private JpaReservationRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findByUserAndStatus(User user, ReservationStatus status) {
        List<ReservationEntity> reservations = this.repository
                .findByCoordinatorAndStatus(UserEntity.fromDomainModel(user), status);
        return reservations.stream()
                .map(ReservationEntity::toDomainModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> findById(UUID id) {
        return this.repository.findById(id).map(ReservationEntity::toDomainModel);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsCollisionOfRealizationAndFinishDates(
            UUID establishmentId,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    ) {
        return this.repository.existsBetweenRealizationDateAndFinishDate(establishmentId, realizationDate, finishDate) > 0;
    }

    @Override
    @Transactional
    public Reservation save(Reservation reservation) {
        return repository.save(ReservationEntity.fromDomainModel(reservation)).toDomainModel();
    }
}
