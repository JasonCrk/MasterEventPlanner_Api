package com.SAR.ReservationsSAR.context.establishment.application.controller;

import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentDetailsResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentItemResponse;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;
import com.SAR.ReservationsSAR.shared.domain.validations.OnlyDateAndHour;

import jakarta.validation.constraints.Future;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EstablishmentController {
    ResponseEntity<EstablishmentDetailsResponse> getEstablishmentDetails(
            @PathVariable("establishmentId") UUID establishmentId
    );

    ResponseEntity<List<TopicResponse>> getEstablishmentTopics(
            @PathVariable("establishmentId") UUID establishmentId
    );

    ResponseEntity<List<EstablishmentItemResponse>> searchAvailableEstablishments(
            @RequestParam(value = "realization", required = false)
            @Future(message = "La fecha de realización debe ser mayor a la fecha actual")
            @OnlyDateAndHour(
                    message = "La fecha de realización solo permite año, mes, día y hora",
                    required = false
            )
            LocalDateTime realizationDate,

            @RequestParam(value = "finish", required = false)
            @Future(message = "La fecha de finalización debe ser mayor a la fecha actual")
            @OnlyDateAndHour(
                    message = "La fecha de finalización solo permite año, mes, día y hora",
                    required = false
            )
            LocalDateTime finishDate,

            @RequestParam(value = "t", required = false)
            UUID topicId
    );
}
