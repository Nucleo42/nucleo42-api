package com.nucleo42.infrastructure.repository;

import com.nucleo42.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);
}
