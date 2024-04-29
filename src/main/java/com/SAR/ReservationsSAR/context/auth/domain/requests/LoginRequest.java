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

    @Email(message = "El correo electrónico es invalido")
    @NotBlank(message = "El correo electrónico es requerido")
    @Size(max = 255, message = "El correo electrónico solo puede tener máximo 255 caracteres")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    @Size(max = 255, message = "La contraseña solo puede tener máximo 255 caracteres")
    private String password;
}
