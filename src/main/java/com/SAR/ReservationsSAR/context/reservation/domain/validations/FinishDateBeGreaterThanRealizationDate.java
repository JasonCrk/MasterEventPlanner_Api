package com.SAR.ReservationsSAR.context.reservation.domain.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FinishDateBeGreaterThanRealizationDateValidator.class)
@Documented
public @interface FinishDateBeGreaterThanRealizationDate {
    String message() default "La fecha de finalización debe ser mayor a la fecha de realización de la reserva";
    boolean required() default true;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
