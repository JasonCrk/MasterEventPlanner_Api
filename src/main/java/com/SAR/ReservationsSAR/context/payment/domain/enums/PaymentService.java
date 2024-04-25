package com.SAR.ReservationsSAR.context.payment.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentService {
    RESERVATION("reservation");

    final String value;

    PaymentService(String value) {
        this.value = value;
    }

}
