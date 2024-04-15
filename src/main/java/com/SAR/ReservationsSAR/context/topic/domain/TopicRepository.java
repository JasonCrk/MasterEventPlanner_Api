package com.SAR.ReservationsSAR.context.topic.domain;

import java.util.Optional;
import java.util.UUID;

public interface TopicRepository {
    Optional<Topic> findById(UUID id);
}
