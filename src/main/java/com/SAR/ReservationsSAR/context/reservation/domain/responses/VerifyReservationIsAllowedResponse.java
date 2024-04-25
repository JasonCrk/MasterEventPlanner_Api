package com.SAR.ReservationsSAR.context.reservation.domain.responses;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyReservationIsAllowedResponse {
    private Establishment establishment;
    private Topic topic;
}
