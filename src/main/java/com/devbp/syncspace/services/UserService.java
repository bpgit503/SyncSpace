package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateUserRequest;
import com.devbp.syncspace.domain.dtos.UserResponseDto;
import com.devbp.syncspace.domain.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User createUser(CreateUserRequest createUserRequestDto);

    User findUserById(Long id);

    User findUserByEmail(String email);

    User updateUser(long id, UserResponseDto.UpdateUserRequest userRequest);

    void deleteUserById(long id);

    void deleteUserByEmail(String email);

    User activateUserByEmail(String email);

    User deactivateUserByEmail(String email);

}
