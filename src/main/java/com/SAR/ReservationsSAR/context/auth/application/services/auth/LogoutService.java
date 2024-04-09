package com.SAR.ReservationsSAR.context.auth.application.services.auth;

import com.SAR.ReservationsSAR.context.auth.domain.SessionTokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final SessionTokenRepository sessionTokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String accessToken;

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return;

        accessToken = authHeader.substring(7);

        var storedToken = this.sessionTokenRepository
                .findByToken(accessToken)
                .orElse(null);

        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            this.sessionTokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
