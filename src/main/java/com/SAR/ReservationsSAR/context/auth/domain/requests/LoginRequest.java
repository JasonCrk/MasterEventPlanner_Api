package com.SAR.ReservationsSAR.context.auth.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @Email(message = "The email is invalid")
    @NotBlank(message = "The email is required")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "The password is required")
    @Size(max = 255)
    private String password;
}
