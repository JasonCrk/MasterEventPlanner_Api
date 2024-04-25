package com.SAR.ReservationsSAR.context.payment.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentCurrency {
    PEN("pen"),
    USD("usd");

    final String value;

    PaymentCurrency(String value) {
        this.value = value;
    }

}
