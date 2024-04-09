package com.SAR.ReservationsSAR.context.auth.infrastructure.controller;

import com.SAR.ReservationsSAR.context.auth.application.requests.LoginRequest;
import com.SAR.ReservationsSAR.context.auth.application.requests.RegisterUserRequest;

import com.SAR.ReservationsSAR.context.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    ResponseEntity<?> getMe(@RequestAttribute("user") User authUser);
    ResponseEntity<?> login(@RequestBody LoginRequest request);
    ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request);
    ResponseEntity<?> verifyToken(HttpServletRequest request);
    ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
