package com.SAR.ReservationsSAR.context.auth.application.requests;

import com.SAR.ReservationsSAR.context.auth.application.validations.IsPhoneNumber.IsPhoneNumber;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "The first name is required")
    @NotNull(message = "The first name is required")
    @Size(max = 60)
    private String firstName;

    @NotBlank(message = "The last name is required")
    @NotNull(message = "The last name is required")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "The birthdate is required")
    @NotNull(message = "The birthdate is required")
    @Past
    private LocalDate birthdate;

    @NotBlank(message = "The phone number is required")
    @NotNull(message = "The phone number is required")
    @IsPhoneNumber
    private String phoneNumber;

    @Email(message = "The email is invalid")
    @NotBlank(message = "The email is required")
    @NotNull(message = "The email is required")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "The password is required")
    @NotNull(message = "The password is required")
    @Size(min = 255)
    private String password;
}
