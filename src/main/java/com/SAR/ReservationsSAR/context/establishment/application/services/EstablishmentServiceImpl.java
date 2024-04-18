package com.SAR.ReservationsSAR.context.establishment.application.services;

import com.SAR.ReservationsSAR.context.establishment.domain.mappers.EstablishmentMapper;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentDetailsResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.responses.EstablishmentItemResponse;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentRepository;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final TopicRepository topicRepository;

    @Override
    @Transactional(readOnly = true)
    public EstablishmentDetailsResponse getEstablishmentDetails(UUID establishmentId) {
        var establishment = this.establishmentRepository
                .findById(establishmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El establecimiento no existe"
                ));

        return EstablishmentMapper.INSTANCE.toDetails(establishment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Topic> getEstablishmentTopics(UUID establishmentId) {
        Establishment establishment = this.establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El establecimiento no existe"));

        return establishment.getTopics();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstablishmentItemResponse> searchAvailableEstablishments(
            LocalDateTime realizationDate,
            LocalDateTime finishDate,
            UUID topicId
    ) {
        if (topicId != null)
            if (!this.topicRepository.existsById(topicId))
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El tema no existe");

        List<Establishment> establishments;

        if (realizationDate == null || finishDate == null) {
            establishments = this.establishmentRepository.findAll();
        } else {
            establishments = this.establishmentRepository.findAvailableEstablishments(
                    realizationDate,
                    finishDate,
                    topicId
            );
        }

        return EstablishmentMapper.INSTANCE.toItemList(establishments);
    }

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
