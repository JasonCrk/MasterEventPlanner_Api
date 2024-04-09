package com.SAR.ReservationsSAR.context.auth.domain;

import com.SAR.ReservationsSAR.context.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionToken {
    private UUID id;
    private String token;
    private TokenType type;
    private boolean revoked;
    private boolean expired;
    private User user;
}
