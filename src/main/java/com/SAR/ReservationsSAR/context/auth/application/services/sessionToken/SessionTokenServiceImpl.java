package com.SAR.ReservationsSAR.context.auth.application.services.sessionToken;

import com.SAR.ReservationsSAR.context.auth.domain.responses.UserAndTokenResponse;
import com.SAR.ReservationsSAR.context.auth.application.services.jwt.JWTService;
import com.SAR.ReservationsSAR.context.auth.domain.SessionToken;
import com.SAR.ReservationsSAR.context.auth.domain.SessionTokenRepository;
import com.SAR.ReservationsSAR.context.auth.domain.TokenType;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SessionTokenServiceImpl implements SessionTokenService {

    private final JWTService jwtService;

    private final SessionTokenRepository sessionTokenRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserAndTokenResponse getUserAndTokenFromHttpRequestHeader(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String token, userEmail;

        if (authHeader == null ||!authHeader.startsWith("Bearer "))
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "La cabecera de autorización no existe o el tipo de token no existe"
            );

        token = authHeader.substring(7);

        userEmail = this.jwtService.extractUsername(token);

        if (userEmail == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "La clave de acceso es invalida");

        var user = this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "La clave de acceso es invalida"));

        return new UserAndTokenResponse(user, token);
    }

    @Override
    @Transactional
    public void saveUserToken(User user, String jwtToken) {
        var token = SessionToken.builder()
                .user(user)
                .token(jwtToken)
                .build();
        sessionTokenRepository.save(token);
    }

    @Override
    @Transactional
    public void revokeAllUserTokens(User user) {
        var validUserSessionTokens = sessionTokenRepository
                .findAllValidSessionTokenByUser(user.getId());

        if (validUserSessionTokens.isEmpty()) return;

        validUserSessionTokens.forEach(sessionToken -> {
            sessionToken.setExpired(true);
            sessionToken.setRevoked(true);
        });

        sessionTokenRepository.saveAll(validUserSessionTokens);
    }
}
