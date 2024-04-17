package com.SAR.ReservationsSAR.context.reservation.domain.validations;

import com.SAR.ReservationsSAR.context.reservation.domain.requests.BaseScheduleReservation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FinishDateBeGreaterThanRealizationDateValidator
        implements ConstraintValidator<FinishDateBeGreaterThanRealizationDate, BaseScheduleReservation> {

    boolean required;

    @Override
    public void initialize(FinishDateBeGreaterThanRealizationDate constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(
            BaseScheduleReservation baseScheduleReservation,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        if (!required &&
                (baseScheduleReservation.getFinishDate() == null ||
                        baseScheduleReservation.getRealizationDate() == null)
        ) return true;
        return baseScheduleReservation.getFinishDate().isAfter(baseScheduleReservation.getRealizationDate());
    }
}
