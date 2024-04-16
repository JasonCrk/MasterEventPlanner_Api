package com.SAR.ReservationsSAR.context.auth.application.services.auth;

import com.SAR.ReservationsSAR.context.auth.domain.requests.LoginRequest;
import com.SAR.ReservationsSAR.context.auth.domain.requests.RegisterUserRequest;
import com.SAR.ReservationsSAR.context.auth.domain.responses.JwtResponse;
import com.SAR.ReservationsSAR.shared.application.responses.MessageResponse;
import com.SAR.ReservationsSAR.context.user.application.responses.UserMe;
import com.SAR.ReservationsSAR.context.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    UserMe getUserByAccessToken(User user);
    JwtResponse registerUser(RegisterUserRequest request);
    JwtResponse login(LoginRequest request);
    JwtResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
    MessageResponse verifyToken(HttpServletRequest request);
}
