package com.SAR.ReservationsSAR.context.auth.infrastructure.persistence.jpa.entities;

import com.SAR.ReservationsSAR.context.auth.domain.SessionToken;
import com.SAR.ReservationsSAR.context.auth.domain.TokenType;
import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "session_token")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SessionTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private boolean revoked;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @PrePersist
    public void setDefaultValues() {
        this.type = TokenType.BEARER;
        this.revoked = false;
        this.expired = false;
    }

    public static SessionTokenEntity fromDomainModel(SessionToken sessionToken) {
        return SessionTokenEntity.builder()
                .id(sessionToken.getId())
                .token(sessionToken.getToken())
                .type(sessionToken.getType())
                .expired(sessionToken.isExpired())
                .revoked(sessionToken.isRevoked())
                .user(UserEntity.fromDomainModel(sessionToken.getUser()))
                .build();
    }

    public SessionToken toDomainModel() {
        return new SessionToken(id, token, type, revoked, expired, user.toDomainModel());
    }
}
