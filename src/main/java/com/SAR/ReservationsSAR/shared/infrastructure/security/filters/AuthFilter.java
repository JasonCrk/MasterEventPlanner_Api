package com.SAR.ReservationsSAR.shared.infrastructure.security.filters;

import com.SAR.ReservationsSAR.context.auth.application.services.jwt.JWTService;
import com.SAR.ReservationsSAR.context.auth.domain.SessionTokenRepository;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.UserRepository;

import com.SAR.ReservationsSAR.shared.infrastructure.filters.FilterErrorResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    private final SessionTokenRepository sessionTokenRepository;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String userEmail, accessToken;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        accessToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(accessToken);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            Optional<User> user = this.userRepository.findByEmail(userDetails.getUsername());

            if (user.isEmpty()) {
                FilterErrorResponse.send(response, "The token is invalid", HttpStatus.UNAUTHORIZED);
                return;
            }

            boolean isAccessTokenValid = this.sessionTokenRepository.findByToken(accessToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);

            if (jwtService.isTokenValid(accessToken, userDetails.getUsername()) && isAccessTokenValid) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                request.setAttribute("user", user.get());
            }
        }

        filterChain.doFilter(request, response);
    }
}
