package com.SAR.ReservationsSAR.context.reservation.application.services;

import com.SAR.ReservationsSAR.context.establishment.application.services.EstablishmentService;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentRepository;
import com.SAR.ReservationsSAR.context.payment.domain.Customer;
import com.SAR.ReservationsSAR.context.payment.domain.ExternalCustomerPaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.ExternalPaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.PaymentCurrency;
import com.SAR.ReservationsSAR.context.reservation.application.mappers.ReservationMapper;
import com.SAR.ReservationsSAR.context.reservation.application.requests.MakeReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.application.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.Reservation;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationRepository;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationStatus;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;
import com.SAR.ReservationsSAR.context.user.domain.User;

import com.SAR.ReservationsSAR.shared.application.responses.MessageResponse;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final EstablishmentService establishmentService;

    private final ReservationRepository reservationRepository;
    private final EstablishmentRepository establishmentRepository;
    private final TopicRepository topicRepository;

    private final ExternalPaymentService paymentService;
    private final ExternalCustomerPaymentService customerPaymentService;

    @Override
    public List<ReservationItemResponse> getAllPendingReservations(User authUser) {
        var reservations = this.reservationRepository.findByUserAndStatus(
                authUser,
                ReservationStatus.PENDING
        );
        return ReservationMapper.INSTANCE.toItemList(reservations);
    }

    @Override
    @Transactional
    public MessageResponse makeReservation(User authUser, MakeReservationRequest request) {
        Establishment establishment = this.establishmentRepository
                .findById(request.getEstablishmentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El establecimiento no existe"
                ));

        Topic topic = this.topicRepository
                .findById(request.getTopicId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El tema no existe"
                ));

        boolean establishmentHasTopic = this.establishmentRepository.hasTopic(establishment.getId(), topic.getId());

        if (!establishmentHasTopic) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El tema no esta relacionado con el establecimiento"
        );

        boolean reservationSchedulingCollisionExists = this.reservationRepository
                .existsCollisionOfRealizationAndFinishDates(
                        establishment.getId(),
                        request.getRealizationDate(),
                        request.getFinishDate()
                );

        if (reservationSchedulingCollisionExists)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Se ha encontrado una colisión de fecha y hora con otra reservación del establecimiento, pruebe usando otra fecha u hora para la reservación del establecimiento"
            );

        Long reservationPrice = this.establishmentService.calculatePriceByReservationHours(
                establishment,
                request.getRealizationDate(),
                request.getFinishDate()
        );

        Customer customer = this.customerPaymentService.findByEmailOrCreate(
                authUser.getFirstName(),
                authUser.getLastName(),
                authUser.getEmail()
        );

        var payment = this.paymentService.cardPay(
                customer,
                reservationPrice,
                PaymentCurrency.PEN
        );

        Reservation newReservation = Reservation.builder()
                .coordinator(authUser)
                .topic(topic)
                .establishment(establishment)
                .finishDate(request.getFinishDate())
                .realizationDate(request.getRealizationDate())
                .payId(payment.getId())
                .status(ReservationStatus.PENDING)
                .build();

        try {
            this.reservationRepository.save(newReservation);
        } catch (Exception e) {
            this.paymentService.cancelPayment(payment.getId());
        }

        return new MessageResponse("Reserva realizada de forma exitosa");
    }

    @Override
    @Transactional
    public MessageResponse cancelReservation(UUID reservationId, UUID authUserId) {
        var reservation = this.reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "La reserva no existe"
                ));

        if (!reservation.getCoordinator().getId().equals(authUserId))
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Usted no tiene permitido realizar la cancelación de la reserva"
            );

        reservation.setStatus(ReservationStatus.CANCELLED);
        this.reservationRepository.save(reservation);

        this.paymentService.cancelPayment(reservation.getPayId());

        return new MessageResponse("Se ha cancelado exitosamente la reserva");
    }
}
