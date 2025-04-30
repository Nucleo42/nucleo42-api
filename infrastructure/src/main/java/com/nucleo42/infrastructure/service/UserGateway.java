package com.nucleo42.infrastructure.service;

import com.nucleo42.application.gateway.AddAccountGateway;
import com.nucleo42.application.gateway.IGetUserProfileByIdGateway;
import com.nucleo42.application.gateway.LoadUserByEmailGateway;
import com.nucleo42.application.gateway.IUpdateUserProfileGateway;
import com.nucleo42.application.gateway.IUpdateUserProfileSkillGateway;
import com.nucleo42.entity.Skill;
import com.nucleo42.entity.User;
import com.nucleo42.exception.UserDoesNotExistException;
import com.nucleo42.infrastructure.entity.UserEntity;
import com.nucleo42.infrastructure.mapper.SkillMapper;
import com.nucleo42.infrastructure.mapper.UserMapper;
import com.nucleo42.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserGateway implements AddAccountGateway, LoadUserByEmailGateway, IGetUserProfileByIdGateway,
        IUpdateUserProfileGateway, IUpdateUserProfileSkillGateway {
    @Autowired
    private UserRepository repository;

    @Override
    public Boolean add(User user) {
        var userEntity = UserMapper.toEntity(user);

        var userExists = this.repository.existsByEmail(userEntity.getEmail());
        if (userExists) {
            return false;
        }

        this.repository.save(userEntity);
        return true;
    }

    @Override
    public Optional<User> load(String email) {
        return this.repository.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public User getUserProfileById(UUID id) {
        return UserMapper.toDomain(findById(id));
    }

    @Override
    public boolean update(User userUpdate) {
        if (userUpdate != null) {
            UserEntity user = findById(userUpdate.getId());
            user = UserMapper.updateUserEntity(user, userUpdate);
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSkills(List<Skill> skills, UUID userId) {
        if (skills != null) {
            UserEntity user = findById(userId);
            user.setSkills(SkillMapper.toSkillEntity(skills));
            repository.save(user);
            return true;
        }
        return false;
    }

    public UserEntity findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new UserDoesNotExistException());
    }

}
