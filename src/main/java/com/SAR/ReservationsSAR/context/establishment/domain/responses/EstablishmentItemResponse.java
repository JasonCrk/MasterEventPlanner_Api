package com.SAR.ReservationsSAR.context.establishment.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentItemResponse {
    private UUID id;
    private String name;
    private int maxCapacity;
    private String firstImage;
}
