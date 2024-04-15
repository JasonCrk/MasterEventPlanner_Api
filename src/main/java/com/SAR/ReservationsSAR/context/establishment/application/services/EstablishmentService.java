package com.SAR.ReservationsSAR.context.establishment.application.services;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;

import java.time.LocalDateTime;

public interface EstablishmentService {
    Long calculatePriceByReservationHours(
            Establishment establishment,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    );
}
