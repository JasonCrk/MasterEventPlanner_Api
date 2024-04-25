package com.SAR.ReservationsSAR.context.payment.domain.ports;

import com.SAR.ReservationsSAR.context.payment.domain.Customer;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentCurrency;
import com.SAR.ReservationsSAR.context.payment.domain.PaymentIntent;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentService;

public interface ExternalPaymentService {
    PaymentIntent createIntent(
            PaymentService service,
            Customer customer,
            Long amount,
            PaymentCurrency currency
    );

    PaymentIntent findById(String paymentId);

    void cancelPayment(String payId);
}
