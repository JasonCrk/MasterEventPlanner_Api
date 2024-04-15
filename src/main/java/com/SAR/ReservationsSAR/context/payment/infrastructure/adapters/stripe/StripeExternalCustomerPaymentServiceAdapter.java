package com.SAR.ReservationsSAR.context.payment.infrastructure.adapters.stripe;

import com.SAR.ReservationsSAR.context.payment.application.exceptions.PaymentException;
import com.SAR.ReservationsSAR.context.payment.domain.Customer;
import com.SAR.ReservationsSAR.context.payment.domain.ExternalCustomerPaymentService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeExternalCustomerPaymentServiceAdapter implements ExternalCustomerPaymentService {

    public StripeExternalCustomerPaymentServiceAdapter(
            @Value("${application.payment.services.stripe.api-key}") String stripeApiKey
    ) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public Customer findByEmail(String email) {
        CustomerSearchParams params = CustomerSearchParams.builder()
                .setQuery("email: '" + email + "'")
                .build();

        CustomerSearchResult searchResult;

        try {
            searchResult = com.stripe.model.Customer.search(params);
        } catch (StripeException e) {
            throw new PaymentException(e.getStripeError().getMessage());
        }

        if (searchResult.getData().isEmpty()) return null;

        var customer = searchResult.getData().get(0);

        return new Customer(customer.getId(), customer.getName(), customer.getEmail());
    }

    @Override
    public Customer create(String firstName, String lastName, String email) {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(firstName + " " + lastName)
                .setEmail(email)
                .build();

        com.stripe.model.Customer customerCreated;

        try {
            customerCreated = com.stripe.model.Customer.create(params);
        } catch (StripeException e) {
            throw new PaymentException(e.getStripeError().getMessage());
        }

        return new Customer(
                customerCreated.getId(),
                customerCreated.getName(),
                customerCreated.getEmail()
        );
    }

    @Override
    public Customer findByEmailOrCreate(String firstName, String lastName, String email) {
        var customer = this.findByEmail(email);

        if (customer != null) return customer;

        return this.create(firstName, lastName, email);
    }
}
