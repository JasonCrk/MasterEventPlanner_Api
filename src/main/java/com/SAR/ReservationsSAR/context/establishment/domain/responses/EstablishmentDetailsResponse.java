package com.SAR.ReservationsSAR.context.establishment.domain.responses;

import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentImage;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDetailsResponse {
    private UUID id;
    private String name;
    private String description;
    private double pricePerHour;
    private int maxCapacity;
    private List<TopicResponse> topics;
    private List<EstablishmentImage> images;
}
