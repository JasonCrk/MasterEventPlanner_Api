package com.SAR.ReservationsSAR.context.topic.domain.mappers;

import com.SAR.ReservationsSAR.context.topic.domain.responses.TopicResponse;
import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    TopicResponse toResponse(Topic topic);
}
