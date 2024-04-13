package com.SAR.ReservationsSAR.context.payment.domain;

public interface ExternalCustomerPaymentService {
    Customer findByEmail(String email);
    Customer create(String firstName, String lastName, String email);
    Customer findByEmailOrCreate(String firstName, String lastName, String email);
}
