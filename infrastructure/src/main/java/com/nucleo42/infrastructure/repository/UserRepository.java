package com.nucleo42.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleo42.infrastructure.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
