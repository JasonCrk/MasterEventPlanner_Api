package com.SAR.ReservationsSAR.context.reservation.application.controllers;

import com.SAR.ReservationsSAR.context.reservation.domain.requests.MakeReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.application.services.ReservationService;
import com.SAR.ReservationsSAR.context.user.domain.User;

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

import java.util.UUID;

@Validated
@RestController
@Tag(name = "Reservation")
@RequestMapping("/api/v1/reservations")
public class ReservationControllerImpl implements ReservationController {

    @Autowired
    private ReservationService service;

    @Override
    @GetMapping("/pending")
    @Operation(summary = "Get all pending reservations")
    public ResponseEntity<?> getAllPendingReservations(User user) {
        return ResponseEntity.ok(this.service.getAllPendingReservations(user));
    }

    @Override
    @PostMapping
    @Operation(summary = "Make reservation")
    public ResponseEntity<?> makeReservation(User user, MakeReservationRequest request) {
        return new ResponseEntity<>(this.service.makeReservation(user, request), HttpStatus.CREATED);
    }

    @Override
    @PostMapping("/{id}/cancel")
    @Operation(summary = "Cancel a reservation")
    public ResponseEntity<?> cancelReservation(UUID reservationId, User user) {
        return ResponseEntity.ok(this.service.cancelReservation(reservationId, user.getId()));
    }
}
