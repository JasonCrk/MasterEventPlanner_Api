package com.SAR.ReservationsSAR.context.auth.application.controller;

import com.SAR.ReservationsSAR.context.auth.domain.requests.LoginRequest;
import com.SAR.ReservationsSAR.context.auth.domain.requests.RegisterUserRequest;
import com.SAR.ReservationsSAR.context.auth.domain.responses.JwtResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.responses.UserMe;
import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    ResponseEntity<UserMe> getMe(@RequestAttribute("user") User authUser);
    ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request);
    ResponseEntity<JwtResponse> registerUser(@Valid @RequestBody RegisterUserRequest request);
    ResponseEntity<MessageResponse> verifyToken(HttpServletRequest request);
    ResponseEntity<JwtResponse> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
