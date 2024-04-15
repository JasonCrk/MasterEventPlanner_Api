package com.SAR.ReservationsSAR.context.payment.domain;

public enum PaymentCurrency {
    PEN("pen"),
    USD("usd");

    private String name;

    private PaymentCurrency(String name) {
        this.name = name;
    }
}
