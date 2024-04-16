package com.SAR.ReservationsSAR.context.establishment.application.responses;

import com.SAR.ReservationsSAR.context.topic.domain.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentItemResponse {
    private UUID id;
    private String name;
    private int maxCapacity;
    private List<Topic> topics;
    private String firstImage;
}
