package com.SAR.ReservationsSAR.context.topic.application.controller;

import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicController {
    ResponseEntity<List<TopicResponse>> getAll();
}
