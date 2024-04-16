package com.SAR.ReservationsSAR.context.establishment.domain.responses;

import com.SAR.ReservationsSAR.context.establishment.domain.EstablishmentImage;

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
    private int maxCapacity;
    private List<EstablishmentImage> images;
}
