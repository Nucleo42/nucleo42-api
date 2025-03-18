package com.nucleo42.infrastruture.mapper;

import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getAcceptTerms(),
                null,
                user.getBiography(),
                null,
                null,
                null,
                null);
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBiography(),
                userEntity.getAceptTerms(),
                null
        );
    }
}
