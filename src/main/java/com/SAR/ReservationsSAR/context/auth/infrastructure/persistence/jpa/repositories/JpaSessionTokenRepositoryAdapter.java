package com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.repositories;

import com.SAR.ReservationsSAR.context.auth.domain.SessionToken;
import com.SAR.ReservationsSAR.context.auth.domain.SessionTokenRepository;
import com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.entities.SessionTokenEntity;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaSessionTokenRepositoryAdapter implements SessionTokenRepository {

    @Autowired
    private JpaSessionTokenRepository jpaRepository;

    @Override
    public List<SessionToken> findAll() {
        return this.jpaRepository.findAll().stream()
                .map(SessionTokenEntity::toDomainModel)
                .toList();
    }

    @Override
    public List<SessionToken> findAllValidSessionTokenByUser(UUID userId) {
        return this.jpaRepository.findAllValidSessionTokenByUser(userId).stream()
                .map(SessionTokenEntity::toDomainModel)
                .toList();
    }

    @Override
    @Transactional
    public Optional<SessionToken> findByToken(String token) {
        var sessionToken = jpaRepository.findByToken(token);
        return sessionToken.map(SessionTokenEntity::toDomainModel);
    }

    @Override
    @Transactional
    public void save(SessionToken sessionToken) {
        this.jpaRepository.save(SessionTokenEntity.fromDomainModel(sessionToken));
    }

    @Override
    @Transactional
    public void saveAll(List<SessionToken> sessionTokens) {
        this.jpaRepository.saveAll(sessionTokens.stream()
                .map(SessionTokenEntity::fromDomainModel)
                .toList()
        );
    }
}
