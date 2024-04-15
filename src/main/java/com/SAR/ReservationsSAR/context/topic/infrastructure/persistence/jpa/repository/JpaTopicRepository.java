package com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.repository;

import com.SAR.ReservationsSAR.context.topic.infrastructure.persistence.jpa.entities.TopicEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTopicRepository extends JpaRepository<TopicEntity, UUID> {
}