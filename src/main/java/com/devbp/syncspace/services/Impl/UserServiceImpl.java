package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
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

    //todo create test cases
    //todo refactor find user by email to a single method call


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(CreateUserRequest createUserRequestDto) {

        if (userRepository.existsByEmail(createUserRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        User newUser = User.builder()
                .email(createUserRequestDto.getEmail())
                .firstName(createUserRequestDto.getFirstName())
                .lastName(createUserRequestDto.getLastName())
                .phoneNumber(createUserRequestDto.getPhoneNumber())
                .dateOfBirth(createUserRequestDto.getDateOfBirth())
                .address(createUserRequestDto.getAddress())
                .userType(createUserRequestDto.getUserType())
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email : " + email));
    }

    @Transactional
    @Override
    public User updateUser(long id, UpdateUserRequest updateUserRequest) {
        User existingUser = findUserById(id);

        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equals(existingUser.getEmail())) {

            if (userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), id)) {

                throw new EmailAlreadyExistsException("Email is in use");

            } else existingUser.setEmail(updateUserRequest.getEmail());
        }

        existingUser.setFirstName(updateUserRequest.getFirstName());
        existingUser.setLastName(updateUserRequest.getLastName());
        existingUser.setPhoneNumber(updateUserRequest.getPhoneNumber());
        existingUser.setDateOfBirth(updateUserRequest.getDateOfBirth());
        existingUser.setAddress(updateUserRequest.getAddress());
        existingUser.setStatus(updateUserRequest.getStatus());

        return userRepository.save(existingUser);
    }

    @Transactional
    @Override
    public void deleteUserByEmail(long id) {
        User user = findUserById(id);

        userRepository.delete(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = findUserByEmail(email);

        userRepository.delete(user);
    }

    @Transactional
    @Override
    public User activateUserByEmail(String email) {
        User user = findUserByEmail(email);

        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User deactivateUserByEmail(String email) {
        User user = findUserByEmail(email);

        user.setStatus(UserStatus.INACTIVE);

        return userRepository.save(user);
    }


}
