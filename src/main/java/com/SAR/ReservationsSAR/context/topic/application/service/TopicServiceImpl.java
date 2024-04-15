package com.SAR.ReservationsSAR.context.topic.application.service;

import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public List<Topic> getAll() {
        return this.topicRepository.findAll();
    }
}
