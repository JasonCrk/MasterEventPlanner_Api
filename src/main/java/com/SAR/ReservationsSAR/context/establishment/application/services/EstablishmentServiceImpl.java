package com.SAR.ReservationsSAR.context.establishment.application.services;

import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    @Override
    public Long calculatePriceByReservationHours(
            Establishment establishment,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    ) {
        long hours = ChronoUnit.HOURS.between(realizationDate, finishDate);
        return hours * establishment.getPricePerHour();
    }
}
