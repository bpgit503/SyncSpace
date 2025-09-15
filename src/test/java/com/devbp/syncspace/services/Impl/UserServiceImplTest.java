package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Implementation Unit Tests")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User expectedUser;
    private CreateUserRequest createUserRequest;
    private UpdateUserRequest updateUserRequest;


    @BeforeEach
    void setUp() {

        this.expectedUser = User.builder()
                .id(1L)
                .email("john.doe@email.com")
                .firstName("john")
                .lastName("doe")
                .phoneNumber("012345678")
                .dateOfBirth(LocalDate.parse("1990-02-02"))
                .address("123 street")
                .userType(UserType.CLIENT)
                .build();


        this.createUserRequest = CreateUserRequest.builder()
                .email("john.doe@email.com")
                .firstName("john")
                .lastName("doe")
                .phoneNumber("012345678")
                .dateOfBirth(LocalDate.parse("1990-02-02"))
                .address("123 street")
                .userType(UserType.CLIENT)
                .build();
    }

    @Nested
    @DisplayName("Create User Tests")
    class createUserTests {

        /*** Test Scenarios:
         * When User is created successfully
         * when inputted email already exists in db
         * check if given email is null
         */

        @Test
        @DisplayName("Should create user successfully when email doesn't exist")
        void shouldCreateUserSuccessfully() {
            // Given
            when(userRepository.existsByEmail(createUserRequest.getEmail())).thenReturn(false);
            when(userRepository.save(any(User.class))).thenReturn(expectedUser);

            // When
            User newUser = userService.createUser(createUserRequest);

            // then
            assertNotNull(newUser);
            assertEquals(expectedUser, newUser);
            verify(userRepository, times(1)).existsByEmail(createUserRequest.getEmail());
            verify(userRepository, times(1)).save(any(User.class));

        }

        @Test
        @DisplayName("Should Throw EmailAlreadyExistsException when email already exists")
        void shouldThrowExceptionWhenEmailAlreadyExists(){
            //Given
            when(userRepository.existsByEmail(createUserRequest.getEmail())).thenReturn(true);

            //When & Then
            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(createUserRequest));

            assertEquals("Email already in use", exception.getMessage());
            verify(userRepository, times(1)).existsByEmail(createUserRequest.getEmail());
            verify(userRepository, never()).save(any(User.class));
        }
    }




}