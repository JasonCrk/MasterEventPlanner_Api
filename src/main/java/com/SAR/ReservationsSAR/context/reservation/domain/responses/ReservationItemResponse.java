package com.SAR.ReservationsSAR.context.reservation.domain.responses;

import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentSimpleResponse;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationItemResponse {
    private UUID id;
    private EstablishmentSimpleResponse establishment;
    private TopicResponse topic;
    private LocalDateTime realizationDate;
    private LocalDateTime finishDate;
    private LocalDateTime createdAt;
    private String status;
}
