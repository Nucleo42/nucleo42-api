package com.nucleo42;

import java.util.UUID;

import com.nucleo42.entity.User;

public interface FindUserProfileByIdCase {
    User findById(UUID userId);
}
