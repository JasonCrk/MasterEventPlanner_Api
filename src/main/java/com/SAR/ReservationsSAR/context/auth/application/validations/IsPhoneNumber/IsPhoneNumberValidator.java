package com.SAR.ReservationsSAR.context.auth.application.validations.IsPhoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsPhoneNumberValidator implements ConstraintValidator<IsPhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber.matches("^[1-9]\\d{8}$");
    }
}
