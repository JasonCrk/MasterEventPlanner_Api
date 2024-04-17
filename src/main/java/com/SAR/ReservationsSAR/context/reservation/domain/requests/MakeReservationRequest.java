package com.SAR.ReservationsSAR.context.reservation.domain.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeReservationRequest {

    @NotNull(message = "El establecimiento es requerido")
    private UUID establishmentId;

    @NotNull(message = "El tema es requerido")
    private UUID topicId;

    @Future(message = "La fecha debe ser mayor a la actual")
    private LocalDateTime realizationDate;

    @Future
    private LocalDateTime finishDate;
}
