package com.nucleo42.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nucleo42.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

}
