package com.SAR.ReservationsSAR.context.establishment.application.controller;

import com.SAR.ReservationsSAR.context.establishment.application.services.EstablishmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Validated
@Tag(name = "Establishment")
@RequestMapping("/api/v1/establishments")
public class EstablishmentControllerImpl implements EstablishmentController {

    @Autowired
    private EstablishmentService service;

    @Override
    @GetMapping("/search/available")
    @Operation(summary = "Search available establishments")
    public ResponseEntity<?> searchAvailableEstablishments(
            LocalDateTime realizationDate,
            LocalDateTime finishDate,
            UUID topicId
    ) {
        return ResponseEntity.ok(this.service.searchAvailableEstablishments(realizationDate, finishDate, topicId));
    }

    @Override
    @GetMapping("/{establishmentId}")
    @Operation(summary = "Get all establishment details by id")
    public ResponseEntity<?> getEstablishmentDetails(UUID establishmentId) {
        return ResponseEntity.ok(this.service.getEstablishmentDetails(establishmentId));
    }

    @Override
    @GetMapping("/{establishmentId}/topics")
    public ResponseEntity<?> getEstablishmentTopics(UUID establishmentId) {
        return ResponseEntity.ok(this.service.getEstablishmentTopics(establishmentId));
    }
}
