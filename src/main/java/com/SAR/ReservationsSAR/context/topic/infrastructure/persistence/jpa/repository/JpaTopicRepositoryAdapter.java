package com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.topic.domain.Topic;
import com.SAR.ReservationsSAR.context.topic.domain.TopicRepository;
import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities.TopicEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTopicRepositoryAdapter implements TopicRepository {

    @Autowired
    private JpaTopicRepository repository;

    @Override
    public Optional<Topic> findById(UUID id) {
        return this.repository.findById(id).map(TopicEntity::toDomainModel);
    }
}
