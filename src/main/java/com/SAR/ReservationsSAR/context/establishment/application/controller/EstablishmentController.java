package com.SAR.ReservationsSAR.context.establishment.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

public interface EstablishmentController {
    ResponseEntity<?> getEstablishmentDetails(
            @PathVariable("establishmentId") UUID establishmentId
    );

    ResponseEntity<?> getEstablishmentTopics(
            @PathVariable("establishmentId") UUID establishmentId
    );

    ResponseEntity<?> searchAvailableEstablishments(
            @RequestParam(value = "realization", required = false) LocalDateTime realizationDate,
            @RequestParam(value = "finish", required = false) LocalDateTime finishDate,
            @RequestParam(value = "t", required = false) UUID topicId
    );
}
