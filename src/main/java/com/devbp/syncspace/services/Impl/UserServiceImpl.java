package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public User createUser(CreateUserRequest createUserRequestDto) {

        if (userRepository.existsByEmail(createUserRequestDto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        User newUser = new User();
        newUser.setEmail(createUserRequestDto.getEmail());
        newUser.setFirstName(createUserRequestDto.getFirstName());
        newUser.setLastName(createUserRequestDto.getLastName());
        newUser.setPhoneNumber(createUserRequestDto.getPhoneNumber());
        newUser.setDateOfBirth(createUserRequestDto.getDateOfBirth());
        newUser.setAddress(createUserRequestDto.getAddress());
        newUser.setUserType(createUserRequestDto.getUserType());

        return userRepository.save(newUser);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }
}
