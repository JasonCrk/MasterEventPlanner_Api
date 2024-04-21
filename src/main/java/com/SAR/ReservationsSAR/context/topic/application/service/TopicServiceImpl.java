package com.SAR.ReservationsSAR.context.topic.application.service;

import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;
import com.SAR.ReservationsSAR.context.topic.domain.mappers.TopicMapper;
import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TopicResponse> getAll() {
        return TopicMapper.INSTANCE.toListResponse(this.topicRepository.findAll());
    }
}
