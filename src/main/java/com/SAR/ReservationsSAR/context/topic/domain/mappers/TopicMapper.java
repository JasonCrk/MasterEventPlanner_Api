package com.SAR.ReservationsSAR.context.topic.domain.mappers;

import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicResponse toResponse(Topic topic);

    List<TopicResponse> toListResponse(List<Topic> topics);
}
