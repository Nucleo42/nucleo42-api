package com.nucleo42.infrastruture.repository;

import com.nucleo42.infrastruture.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Boolean existsByEmail(String email);
}
