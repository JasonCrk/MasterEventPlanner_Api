package com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.repositories;

import com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.entities.SessionTokenEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaSessionTokenRepository extends JpaRepository<SessionTokenEntity, UUID> {
    @Query(value = """
            SELECT t FROM SessionTokenEntity t INNER JOIN UserEntity u\s
            ON t.user.id = u.id\s
            WHERE u.id = :id and (t.expired = false and t.revoked = false)\s
            """)
    List<SessionTokenEntity> findAllValidSessionTokenByUser(UUID id);

    Optional<SessionTokenEntity> findByToken(String token);
}
