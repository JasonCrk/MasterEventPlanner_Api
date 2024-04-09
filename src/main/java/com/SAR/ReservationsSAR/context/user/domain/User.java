package com.SAR.ReservationsSAR.context.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String phoneNumber;
    private UserRole role;
    private String email;
    private String password;

    public String getUsername() {
        return this.email;
    }
}
