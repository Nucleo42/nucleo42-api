package com.nucleo42.infrastruture.mapper;

import com.nucleo42.entity.User;
import com.nucleo42.infrastruture.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return new UserEntity(
                null,
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
}
