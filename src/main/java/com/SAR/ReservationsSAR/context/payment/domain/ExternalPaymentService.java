package com.SAR.ReservationsSAR.context.payment.domain;

public interface ExternalPaymentService {
    PaymentResponse cardPay(
            Customer customer,
            Long amount,
            PaymentCurrency currency
    );
    void cancelPayment(String payId);
}
