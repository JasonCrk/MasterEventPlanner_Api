package com.SAR.ReservationsSAR.shared.application.filters;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FilterErrorResponse {
    public static void send(HttpServletResponse response, String message, HttpStatus statusCode) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("code", statusCode.value());

        response.setContentType("application/json");
        response.setStatus(statusCode.value());

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
