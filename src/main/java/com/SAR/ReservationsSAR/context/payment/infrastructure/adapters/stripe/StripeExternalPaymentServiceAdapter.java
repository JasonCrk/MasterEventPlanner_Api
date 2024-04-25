package com.SAR.ReservationsSAR.context.payment.infrastructure.adapters.stripe;

import com.SAR.ReservationsSAR.context.payment.application.exceptions.PaymentException;
import com.SAR.ReservationsSAR.context.payment.domain.*;
import com.SAR.ReservationsSAR.context.payment.domain.Customer;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentCurrency;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.ports.ExternalPaymentService;
import com.SAR.ReservationsSAR.context.payment.infrastructure.mappers.PaymentIntentMapper;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.param.PaymentIntentCancelParams;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripeExternalPaymentServiceAdapter implements ExternalPaymentService {

    public StripeExternalPaymentServiceAdapter(
            @Value("${application.payment.services.stripe.api-key}") String stripeApiKey
    ) {
        Stripe.apiKey = stripeApiKey;
    }

    @Override
    public PaymentIntent createIntent(
            PaymentService service,
            Customer customer,
            Long amount,
            PaymentCurrency currency
    ) {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCustomer(customer.getId())
                .setCurrency(currency.getValue())
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .build()
                )
                .setReceiptEmail(customer.getEmail())
                .putMetadata("service", service.getValue())
                .build();

        try {
            com.stripe.model.PaymentIntent paymentIntent = com.stripe.model.PaymentIntent.create(params);
            return PaymentIntentMapper.INSTANCE.toDomain(paymentIntent);
        } catch (StripeException e) {
            throw new PaymentException(e.getStripeError().getMessage());
        }
    }

    @Override
    public PaymentIntent findById(String paymentId) {
        try {
            com.stripe.model.PaymentIntent paymentIntent = com.stripe.model.PaymentIntent.retrieve(paymentId);
            return PaymentIntentMapper.INSTANCE.toDomain(paymentIntent);
        } catch (StripeException e) {
            throw new PaymentException(e.getStripeError().getMessage());
        }
    }

    @Override
    public void cancelPayment(String payId) {
        try {
            com.stripe.model.PaymentIntent payment = com.stripe.model.PaymentIntent.retrieve(payId);
            payment.cancel(PaymentIntentCancelParams.builder().build());
        } catch (StripeException e) {
            throw new PaymentException(e.getStripeError().getMessage());
        }
    }
}
