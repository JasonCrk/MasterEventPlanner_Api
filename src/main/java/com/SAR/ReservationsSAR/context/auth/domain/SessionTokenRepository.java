package com.SAR.ReservationsSAR.context.auth.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionTokenRepository {
    List<SessionToken> findAll();
    List<SessionToken> findAllValidSessionTokenByUser(UUID userId);

    Optional<SessionToken> findByToken(String token);

    void save(SessionToken sessionToken);
    void saveAll(List<SessionToken> sessionTokens);
}
