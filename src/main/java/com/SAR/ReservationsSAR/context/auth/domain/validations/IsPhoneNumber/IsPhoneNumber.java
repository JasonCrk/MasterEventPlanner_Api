package com.SAR.ReservationsSAR.context.auth.domain.validations.IsPhoneNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsPhoneNumberValidator.class)
@Documented
public @interface IsPhoneNumber {
    String message() default "El número de teléfono es invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
