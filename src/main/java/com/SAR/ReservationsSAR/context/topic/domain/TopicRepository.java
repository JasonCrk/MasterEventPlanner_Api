package com.SAR.ReservationsSAR.context.topic.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TopicRepository {
    List<Topic> findAll();
    Optional<Topic> findById(UUID id);
    boolean existsById(UUID id);
}
