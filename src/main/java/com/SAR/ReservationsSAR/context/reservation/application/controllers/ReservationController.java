package com.SAR.ReservationsSAR.context.reservation.application.controllers;

import com.SAR.ReservationsSAR.context.payment.domain.responses.PaymentResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.ConfirmReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.CreateReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;

import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ReservationController {
    ResponseEntity<List<ReservationItemResponse>> getAllPendingReservations(
            @RequestAttribute("user") User user
    );

    ResponseEntity<MessageResponse> confirmReservation(
            @RequestAttribute("user") User user,
            @Valid @RequestBody ConfirmReservationRequest request
    );

    ResponseEntity<PaymentResponse> createReservationPayment(
            @RequestAttribute("user") User user,
            @Valid @RequestBody CreateReservationRequest request
    );

    ResponseEntity<MessageResponse> cancelReservation(
            @PathVariable("id") UUID reservationId,
            @RequestAttribute("user") User user
    );
}
