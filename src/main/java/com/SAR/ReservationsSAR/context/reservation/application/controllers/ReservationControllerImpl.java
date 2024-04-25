package com.SAR.ReservationsSAR.context.reservation.application.controllers;

import com.SAR.ReservationsSAR.context.payment.domain.responses.PaymentResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.ConfirmReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.CreateReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.application.services.ReservationService;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;

import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@Tag(name = "Reservation")
@RequestMapping("/api/v1/reservations")
public class ReservationControllerImpl implements ReservationController {

    @Autowired
    private ReservationService service;

    @Override
    @GetMapping
    @Operation(summary = "Get all pending reservations")
    public ResponseEntity<List<ReservationItemResponse>> getAllPendingReservations(User user) {
        return ResponseEntity.ok(this.service.getAllPendingReservations(user));
    }

    @Override
    @PostMapping("/checkout/confirm")
    @Operation(summary = "Confirm payment and create reservation")
    public ResponseEntity<MessageResponse> confirmReservation(User user, ConfirmReservationRequest request) {
        return new ResponseEntity<>(this.service.confirmReservation(user, request), HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/checkout")
    @Operation(summary = "Create reservation payment intent")
    public ResponseEntity<PaymentResponse> createReservationPayment(User user, CreateReservationRequest request) {
        return ResponseEntity.ok(this.service.createReservationPayment(user, request));
    }

    @Override
    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel a reservation")
    public ResponseEntity<MessageResponse> cancelReservation(UUID reservationId, User user) {
        return ResponseEntity.ok(this.service.cancelReservation(reservationId, user.getId()));
    }
}
