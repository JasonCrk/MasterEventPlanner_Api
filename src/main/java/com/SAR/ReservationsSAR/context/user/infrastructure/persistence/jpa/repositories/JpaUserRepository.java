package com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.repositories;

import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
