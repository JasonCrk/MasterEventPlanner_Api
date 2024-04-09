package com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.repositories;

import com.SAR.ReservationsSAR.context.user.domain.User;
import com.SAR.ReservationsSAR.context.user.domain.UserRepository;
import com.SAR.ReservationsSAR.context.user.infrastructure.persistence.jpa.entities.UserEntity;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JpaUserRepositoryAdapter implements UserRepository {

    @Autowired
    private JpaUserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(UserEntity::toDomainModel)
                .toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> user = repository.findByEmail(email);
        return user.map(UserEntity::toDomainModel);
    }

    @Override
    @Transactional
    public User save(User user) {
        UserEntity savedUser = this.repository.save(UserEntity.fromDomainModel(user));
        return savedUser.toDomainModel();
    }
}
