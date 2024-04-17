package com.SAR.ReservationsSAR.context.reservation.domain.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MakeReservationRequest extends BaseScheduleReservation {

    @NotNull(message = "El establecimiento es requerido")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "El ID del establecimiento es invalido"
    )
    private String establishmentId;

    @NotNull(message = "El tema es requerido")
    @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "El ID del tema es invalido"
    )
    private String topicId;

    public UUID getEstablishmentId() {
        return UUID.fromString(this.establishmentId);
    }

    public UUID getTopicId() {
        return UUID.fromString(this.topicId);
    }
}
