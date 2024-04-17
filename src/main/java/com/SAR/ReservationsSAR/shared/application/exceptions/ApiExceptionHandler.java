package com.SAR.ReservationsSAR.shared.application.exceptions;

import com.SAR.ReservationsSAR.context.payment.application.exceptions.PaymentException;
import com.SAR.ReservationsSAR.shared.domain.responses.ApiErrorResponse;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            WebRequest request
    ) {
        Map<String, List<String>> errorResponse = new HashMap<>();

        List<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage).toList();

        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleConstraintViolationException(
            ConstraintViolationException exception,
            WebRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        for (var error : exception.getConstraintViolations()) {
            String param = error.getPropertyPath().toString().split("\\.")[1];
            errors.put(param, error.getMessage());
        }

        Map<String, Map<String, String>> errorResponse = new HashMap<>();

        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

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
