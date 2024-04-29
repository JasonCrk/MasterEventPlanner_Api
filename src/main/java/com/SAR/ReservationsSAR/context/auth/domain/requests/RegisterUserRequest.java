package com.SAR.ReservationsSAR.context.auth.domain.requests;

import com.SAR.ReservationsSAR.context.auth.domain.validations.IsPhoneNumber.IsPhoneNumber;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 60, message = "Máximo 60 caracteres")
    private String firstName;

    @NotBlank(message = "Los apellidos son requeridos")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String lastName;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento no puede ser mayor a la actual")
    private LocalDate birthdate;

    @NotBlank(message = "El número de teléfono es requerido")
    @IsPhoneNumber
    private String phoneNumber;

    @Email(message = "El correo electrónico es invalido")
    @NotBlank(message = "El correo electrónico es requerido")
    @Size(max = 255, message = "Máximo 255 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 255, message = "Máximo 255 caracteres")
    private String password;
}
