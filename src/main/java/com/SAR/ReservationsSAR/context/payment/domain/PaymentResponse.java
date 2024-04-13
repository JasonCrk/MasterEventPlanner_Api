package com.SAR.ReservationsSAR.context.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private String clientSecret;
}
