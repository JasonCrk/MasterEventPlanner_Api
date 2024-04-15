package com.SAR.ReservationsSAR.context.establishment.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentSimpleResponse {
    private UUID id;
    private String name;
    private String description;
    private String firstImage;
}
