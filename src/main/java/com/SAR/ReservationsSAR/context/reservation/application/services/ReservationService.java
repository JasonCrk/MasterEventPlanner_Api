package com.SAR.ReservationsSAR.context.reservation.application.services;

import com.SAR.ReservationsSAR.context.payment.domain.responses.PaymentResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.ConfirmReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.requests.CreateReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.reservation.domain.responses.VerifyReservationIsAllowedResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.shared.domain.responses.MessageResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<ReservationItemResponse> getAllPendingReservations(User authUser);
    PaymentResponse createReservationPayment(User authUser, CreateReservationRequest request);
    MessageResponse confirmReservation(User authUser, ConfirmReservationRequest request);
    MessageResponse cancelReservation(UUID reservationId, UUID authUserId);

    VerifyReservationIsAllowedResponse verifyReservationIsAllowed(
            UUID establishmentId,
            UUID topicId,
            LocalDateTime realizationDate,
            LocalDateTime finishDate
    );
}
