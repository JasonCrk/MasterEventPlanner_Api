package com.SAR.ReservationsSAR.context.payment.domain.ports;

import com.SAR.ReservationsSAR.context.payment.domain.Customer;

public interface ExternalCustomerPaymentService {
    Customer findByEmail(String email);
    Customer create(String firstName, String lastName, String email);
    Customer findByEmailOrCreate(String firstName, String lastName, String email);
}
