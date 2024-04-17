package com.SAR.ReservationsSAR.context.reservation.domain.validations;

import com.SAR.ReservationsSAR.context.reservation.domain.requests.BaseScheduleReservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RealizationDateAndFinishDateNotEqualsValidator implements
        ConstraintValidator<RealizationDateAndFinishDateNotEquals, BaseScheduleReservation> {

    boolean required;

    @Override
    public void initialize(RealizationDateAndFinishDateNotEquals constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(BaseScheduleReservation baseScheduleReservation, ConstraintValidatorContext constraintValidatorContext) {
        if (!required &&
                (baseScheduleReservation.getRealizationDate() == null ||
                        baseScheduleReservation.getFinishDate() == null)
        ) return true;

        return !baseScheduleReservation.getFinishDate().isEqual(baseScheduleReservation.getRealizationDate());
    }
}
