package com.SAR.ReservationsSAR.context.reservation.application.services;

import com.SAR.ReservationsSAR.context.establishment.application.services.EstablishmentService;
import com.SAR.ReservationsSAR.context.establishment.domain.Establishment;
import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentRepository;
import com.SAR.ReservationsSAR.context.payment.domain.*;
import com.SAR.ReservationsSAR.context.payment.domain.Customer;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentCurrency;
import com.SAR.ReservationsSAR.context.payment.domain.enums.PaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.ports.ExternalCustomerPaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.ports.ExternalPaymentService;
import com.SAR.ReservationsSAR.context.payment.domain.responses.PaymentResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.ConfirmReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.Reservation;
import com.SAR.ReservationsSAR.context.reservation.domain.mappers.ReservationMapper;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.CreateReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationRepository;
import com.SAR.ReservationsSAR.context.reservation.domain.ReservationStatus;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.VerifyReservationIsAllowedResponse;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;
import com.SAR.ReservationsSAR.context.user.domain.User;

import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
    @Transactional(readOnly = true)
    public List<ReservationItemResponse> getAllPendingReservations(User authUser) {
        var reservations = this.reservationRepository.findByUserAndStatus(
                authUser,
                ReservationStatus.PENDING
        );
        return ReservationMapper.INSTANCE.toItemList(reservations);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentResponse createReservationPayment(User authUser, CreateReservationRequest request) {
        VerifyReservationIsAllowedResponse verificationResponse = this.verifyReservationIsAllowed(
                request.getEstablishmentId(),
                request.getTopicId(),
                request.getRealizationDate(),
                request.getFinishDate()
        );

        long amount = this.establishmentService.calculatePriceByReservationHours(
                verificationResponse.getEstablishment(),
                request.getRealizationDate(),
                request.getFinishDate()
        );

        Customer customer = this.customerPaymentService.findByEmailOrCreate(
                authUser.getFirstName(),
                authUser.getLastName(),
                authUser.getEmail()
        );

        PaymentIntent response = this.paymentService.createIntent(
                PaymentService.RESERVATION,
                customer,
                amount,
                PaymentCurrency.PEN
        );

        return new PaymentResponse(response.getClientSecret());
    }

    @Override
    @Transactional
    public MessageResponse confirmReservation(User authUser, ConfirmReservationRequest request) {
        VerifyReservationIsAllowedResponse verificationResponse = this.verifyReservationIsAllowed(
                request.getEstablishmentId(),
                request.getTopicId(),
                request.getRealizationDate(),
                request.getFinishDate()
        );

        var establishment = verificationResponse.getEstablishment();
        var topic = verificationResponse.getTopic();

        PaymentIntent paymentIntent = this.paymentService.findById(request.getPaymentId());

        String paymentService = paymentIntent.getMetadata().get("service");

        if (!paymentService.equals(PaymentService.RESERVATION.getValue()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El pago debe ser para una reserva");

        if (!paymentIntent.getStatus().equals("succeeded"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El pago no se ha realizado todavía");

        var reservationWithPayIdExist = this.reservationRepository.findByPaymentId(paymentIntent.getId());

        if (reservationWithPayIdExist.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El pago no puede pertenecer a otra reserva");

        Customer customer = this.customerPaymentService.findByEmail(authUser.getEmail());

        if (customer == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El pago debe ser de su pertenencia");

        Reservation reservation = Reservation.builder()
                .payId(paymentIntent.getId())
                .coordinator(authUser)
                .establishment(establishment)
                .topic(topic)
                .realizationDate(request.getRealizationDate())
                .finishDate(request.getFinishDate())
                .build();

        this.reservationRepository.save(reservation);

        return new MessageResponse(
                "Muchas gracias " + authUser.getFirstName() + " " + authUser.getLastName() + " por realizar su reserva en el establecimiento " + establishment.getName()
        );
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

    @Override
    public VerifyReservationIsAllowedResponse verifyReservationIsAllowed(
            UUID establishmentId,
            UUID topicId,
            LocalDateTime realizationDate,
            LocalDateTime finishDate)
    {
        Establishment establishment = this.establishmentRepository
                .findById(establishmentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El establecimiento no existe"
                ));

        Topic topic = this.topicRepository
                .findById(topicId)
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
                        realizationDate,
                        finishDate
                );

        if (reservationSchedulingCollisionExists)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Se ha encontrado una colisión de fecha y hora con otra reservación del establecimiento, pruebe usando otra fecha u hora para la reservación del establecimiento"
            );

        return new VerifyReservationIsAllowedResponse(establishment, topic);
    }
}
