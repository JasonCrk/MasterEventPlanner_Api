package com.SAR.ReservationsSAR.context.establishment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentImage {
    private UUID id;
    private String imageUrl;
}
