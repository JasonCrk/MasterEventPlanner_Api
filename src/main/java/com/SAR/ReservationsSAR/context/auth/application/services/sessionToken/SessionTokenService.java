package com.SAR.ReservationsSAR.context.auth.application.services.sessionToken;

import com.SAR.ReservationsSAR.context.auth.domain.responses.UserAndTokenResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;

public interface SessionTokenService {
    UserAndTokenResponse getUserAndTokenFromHttpRequestHeader(HttpServletRequest request);
    void saveUserToken(User user, String jwtToken);
    void revokeAllUserTokens(User user);
}
