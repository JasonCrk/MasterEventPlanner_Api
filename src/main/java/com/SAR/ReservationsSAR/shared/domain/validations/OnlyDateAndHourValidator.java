package com.SAR.ReservationsSAR.shared.domain.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class OnlyDateAndHourValidator implements ConstraintValidator<OnlyDateAndHour, LocalDateTime> {

    boolean required;

    @Override
    public void initialize(OnlyDateAndHour constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (!required && localDateTime == null) return true;
        return localDateTime.getSecond() == 0 && localDateTime.getNano() == 0 && localDateTime.getMinute() == 0;
    }
}
