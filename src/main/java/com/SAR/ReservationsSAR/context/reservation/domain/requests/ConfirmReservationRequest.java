package com.SAR.ReservationsSAR.context.reservation.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmReservationRequest extends CreateReservationRequest {
    private String paymentId;
}
