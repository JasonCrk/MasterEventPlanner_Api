package com.SAR.ReservationsSAR.context.auth.domain.responses;

import com.SAR.ReservationsSAR.context.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAndTokenResponse {
    private User user;
    private String token;
}
