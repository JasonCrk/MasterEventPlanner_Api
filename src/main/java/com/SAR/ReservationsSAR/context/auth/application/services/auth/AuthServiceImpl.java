package com.SAR.ReservationsSAR.context.auth.application.services.auth;

import com.SAR.ReservationsSAR.context.auth.application.requests.LoginRequest;
import com.SAR.ReservationsSAR.context.auth.application.requests.RegisterUserRequest;
import com.SAR.ReservationsSAR.context.auth.application.responses.JwtResponse;
import com.SAR.ReservationsSAR.context.auth.application.services.jwt.JWTService;
import com.SAR.ReservationsSAR.context.auth.application.services.sessionToken.SessionTokenService;
import com.SAR.ReservationsSAR.context.user.application.responses.UserMe;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.UserRepository;
import com.SAR.ReservationsSAR.context.user.domain.UserRole;

import com.SAR.ReservationsSAR.shared.application.responses.MessageResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JWTService jwtService;
    private final SessionTokenService sessionTokenService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserMe getUserByAccessToken(User user) {
        return new UserMe(user.getId(), user.getFirstName(), user.getLastName());
    }

    @Override
    public JwtResponse registerUser(RegisterUserRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthdate(request.getBirthdate())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .role(UserRole.USER)
                .build();

        var savedUser = this.userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        sessionTokenService.saveUserToken(savedUser, jwtToken);

        return new JwtResponse(jwtToken, refreshToken);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user does not exist"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                request.getPassword()
        ));

        String accessToken = this.jwtService.generateToken(user);
        String refreshToken = this.jwtService.generateRefreshToken(user);

        this.sessionTokenService.revokeAllUserTokens(user);
        this.sessionTokenService.saveUserToken(user, accessToken);

        return new JwtResponse(accessToken, refreshToken);
    }

    @Override
    public JwtResponse refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        var authorizationData = this.sessionTokenService
                .getUserAndTokenFromHttpRequestHeader(request);

        var accessToken = this.jwtService.generateToken(authorizationData.getUser());

        this.sessionTokenService.revokeAllUserTokens(authorizationData.getUser());
        this.sessionTokenService.saveUserToken(authorizationData.getUser(), accessToken);

        return new JwtResponse(accessToken, authorizationData.getToken());
    }

    @Override
    public MessageResponse verifyToken(HttpServletRequest request) {
        var authorizationData = this.sessionTokenService
                .getUserAndTokenFromHttpRequestHeader(request);

        if (!jwtService.isTokenValid(authorizationData.getToken(), authorizationData.getUser().getUsername()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The token is invalid");

        return new MessageResponse("The token is valid");
    }
}
