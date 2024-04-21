package com.SAR.ReservationsSAR.context.topic.application.service;

import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import java.util.List;

public interface TopicService {
    List<TopicResponse> getAll();
}
