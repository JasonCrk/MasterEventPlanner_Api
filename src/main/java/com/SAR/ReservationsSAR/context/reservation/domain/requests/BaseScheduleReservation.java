package com.SAR.ReservationsSAR.context.reservation.domain.requests;

import com.SAR.ReservationsSAR.context.reservation.domain.validations.FinishDateBeGreaterThanRealizationDate;
import com.SAR.ReservationsSAR.context.reservation.domain.validations.RealizationDateAndFinishDateNotEquals;

import com.SAR.ReservationsSAR.shared.domain.validations.OnlyDateAndHour;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RealizationDateAndFinishDateNotEquals
@FinishDateBeGreaterThanRealizationDate
public abstract class BaseScheduleReservation {
    @NotNull(message = "La fecha de realización es requerida")
    @Future(message = "La fecha debe ser mayor a la fecha actual")
    @OnlyDateAndHour(message = "La fecha de realización debe tener solo año, mes, día y hora exacta")
    protected LocalDateTime realizationDate;

    @NotNull(message = "La fecha de finalización es requerida")
    @Future(message = "La fecha debe ser mayor a la fecha actual")
    @OnlyDateAndHour(message = "La fecha de finalización debe tener solo año, mes, día y hora exacta")
    protected LocalDateTime finishDate;
}
