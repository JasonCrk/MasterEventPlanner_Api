package com.SAR.ReservationsSAR.context.establishment.application.services;

import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentDetailsResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentItemResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EstablishmentService {
    EstablishmentDetailsResponse getEstablishmentDetails(UUID establishmentId);

    List<TopicResponse> getEstablishmentTopics(UUID establishmentId);

    List<EstablishmentItemResponse> searchAvailableEstablishments(
            LocalDateTime realizationDate,
            LocalDateTime finishDate,
            UUID topicId
    );

    Long calculatePriceByReservationHours(
            Establishment establishment,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    );
}
