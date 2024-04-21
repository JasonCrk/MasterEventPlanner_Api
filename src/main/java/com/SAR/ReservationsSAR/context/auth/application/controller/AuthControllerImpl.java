package com.SAR.ReservationsSAR.context.auth.application.controller;

import com.SAR.ReservationsSAR.context.auth.domain.requests.LoginRequest;
import com.SAR.ReservationsSAR.context.auth.domain.requests.RegisterUserRequest;
import com.SAR.ReservationsSAR.context.auth.application.services.auth.AuthService;
import com.SAR.ReservationsSAR.context.auth.domain.responses.JwtResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.responses.UserMe;
import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthService service;

    @Override
    @GetMapping("/me")
    @Operation(summary = "Get user data by access token")
    public ResponseEntity<UserMe> getMe(User authUser) {
        return ResponseEntity.ok(this.service.getUserByAccessToken(authUser));
    }

    @Override
    @PostMapping("/login")
    @Operation(summary = "Start a new session")
    public ResponseEntity<JwtResponse> login(LoginRequest request) {
        return ResponseEntity.ok(this.service.login(request));
    }

    @Override
    @PostMapping("/register")
    @Operation(summary = "Create a new account")
    public ResponseEntity<JwtResponse> registerUser(RegisterUserRequest request) {
        return ResponseEntity.ok(this.service.registerUser(request));
    }

    @Override
    @PostMapping("/verify")
    @Operation(summary = "Verify that the token is valid")
    public ResponseEntity<MessageResponse> verifyToken(HttpServletRequest request) {
        return ResponseEntity.ok(this.service.verifyToken(request));
    }

    @Override
    @PostMapping("/refresh")
    @Operation(summary = "Generate a new access token using the refresh token")
    public ResponseEntity<JwtResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(this.service.refreshToken(request, response));
    }
}
