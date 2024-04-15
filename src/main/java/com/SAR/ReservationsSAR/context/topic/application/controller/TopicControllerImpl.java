package com.SAR.ReservationsSAR.context.topic.application.controller;

import com.SAR.ReservationsSAR.context.topic.application.service.TopicService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Topic")
@RequestMapping("/api/v1/topics")
public class TopicControllerImpl implements TopicController {

    @Autowired
    private TopicService service;

    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }
}
