package com.SAR.ReservationsSAR.shared.domain.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyDateAndHourValidator.class)
@Documented
public @interface OnlyDateAndHour {
    String message() default "La fecha debe tener solo año, mes, dia y hora exacta";
    boolean required() default true;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
