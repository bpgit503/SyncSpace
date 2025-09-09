package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user, CreateUserRequest createUserRequestDto) {

        User newUser = new User();
        newUser.setEmail(createUserRequestDto.getEmail());
        newUser.setFirstName(createUserRequestDto.getFirstName());
        newUser.setLastName(createUserRequestDto.getLastName());
        newUser.setPhoneNumber(createUserRequestDto.getPhoneNumber());
        newUser.setDateOfBirth(createUserRequestDto.getDateOfBirth());
        newUser.setAddress(createUserRequestDto.getAddress());

        return userRepository.save(user);
    }
}
