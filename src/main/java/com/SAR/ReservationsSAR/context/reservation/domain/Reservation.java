package com.SAR.ReservationsSAR.context.reservation.domain;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private UUID id;
    private User coordinator;
    private Establishment establishment;
    private Topic topic;
    private String payId;
    private LocalDateTime realizationDate;
    private LocalDateTime finishDate;
    private LocalDateTime createdAt;
    private ReservationStatus status;
}
