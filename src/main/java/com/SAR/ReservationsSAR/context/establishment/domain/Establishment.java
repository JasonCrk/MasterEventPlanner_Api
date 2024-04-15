package com.SAR.ReservationsSAR.context.establishment.domain;

import com.SAR.ReservationsSAR.context.topic.domain.Topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Establishment {
    private UUID id;
    private String name;
    private String description;
    private int pricePerHour;
    private List<EstablishmentImage> images;
    private List<Topic> topics;
    private int maxCapacity;
}
