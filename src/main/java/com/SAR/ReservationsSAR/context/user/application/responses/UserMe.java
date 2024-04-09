package com.SAR.ReservationsSAR.context.user.application.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMe {
    private UUID id;
    private String firstName;
    private String lastName;
}
