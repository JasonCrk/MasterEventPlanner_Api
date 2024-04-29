package com.SAR.ReservationsSAR.context.topic.application.controller;

import com.SAR.ReservationsSAR.context.topic.application.service.TopicService;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Topic")
@Validated
@RestController
@RequestMapping("/api/v1/topics")
public class TopicControllerImpl implements TopicController {

    @Autowired
    private TopicService service;

    @Override
    @GetMapping
    @Operation(summary = "Get all topics")
    public ResponseEntity<List<TopicResponse>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
