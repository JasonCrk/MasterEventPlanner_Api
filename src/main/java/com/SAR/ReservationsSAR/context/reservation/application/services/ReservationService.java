package com.SAR.ReservationsSAR.context.reservation.application.services;

import com.SAR.ReservationsSAR.context.reservation.application.requests.MakeReservationRequest;
import com.SAR.ReservationsSAR.context.reservation.application.responses.ReservationItemResponse;
import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.shared.application.responses.MessageResponse;

import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<ReservationItemResponse> getAllPendingReservations(User authUser);
    MessageResponse makeReservation(User authUser, MakeReservationRequest request);
    MessageResponse cancelReservation(UUID reservationId, UUID authUserId);
}
