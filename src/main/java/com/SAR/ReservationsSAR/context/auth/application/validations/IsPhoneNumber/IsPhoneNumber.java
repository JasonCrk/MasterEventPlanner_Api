package com.SAR.ReservationsSAR.context.auth.application.validations.IsPhoneNumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsPhoneNumberValidator.class)
@Documented
public @interface IsPhoneNumber {
    String message() default "The phone number is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
