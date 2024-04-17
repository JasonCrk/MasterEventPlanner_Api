package com.SAR.ReservationsSAR.shared.application.exceptions;

import com.SAR.ReservationsSAR.context.payment.application.exceptions.PaymentException;
import com.SAR.ReservationsSAR.shared.domain.responses.ApiErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ApiErrorResponse> handlerPaymentException(
            PaymentException exception,
            WebRequest request
    ) {
        var response =  new ApiErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
