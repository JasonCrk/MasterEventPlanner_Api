package com.SAR.ReservationsSAR.context.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIntent {
    private String id;
    private Map<String, String> metadata;
    private long amount;
    private String currency;
    private String customer;
    private String description;
    private String status;
    private String clientSecret;
}
