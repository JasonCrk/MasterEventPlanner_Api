package com.SAR.ReservationsSAR.context.reservation.domain.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RealizationDateAndFinishDateNotEqualsValidator.class)
@Documented
public @interface RealizationDateAndFinishDateNotEquals {
    String message() default "La fecha de realización y finalización no pueden ser iguales";
    boolean required() default true;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
