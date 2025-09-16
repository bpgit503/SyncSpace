package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.UserRepository;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

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
    private User updatedUser;
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

        this.updateUserRequest = UpdateUserRequest.builder()
                .email("jane.doe@email.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("987654321")
                .dateOfBirth(LocalDate.parse("1995-06-09"))
                .address("321 street")
                .status(UserStatus.ACTIVE)
                .build();

        this.updatedUser = User.builder()
                .email("jane.doe@email.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("987654321")
                .dateOfBirth(LocalDate.parse("1995-06-09"))
                .address("321 street")
                .status(UserStatus.ACTIVE)
                .build();
    }


    @Nested
    @DisplayName("Create User Tests")
    class createUserTests {

        //TODO create test to see if json object is mapped correctly to DTO


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
        void shouldThrowExceptionWhenEmailAlreadyExists() {
            //Given
            when(userRepository.existsByEmail(createUserRequest.getEmail())).thenReturn(true);

            //When & Then
            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(createUserRequest));

            assertEquals("Email already in use", exception.getMessage());
            verify(userRepository, times(1)).existsByEmail(createUserRequest.getEmail());
            verify(userRepository, never()).save(any(User.class));
        }
    }

    /*
     * TODO create test to verify if all fields are update correctly
     */

    @Nested
    @DisplayName("Update User Tests")
    class updateUserTests {

        @Test
        @DisplayName("Should successfully updated a user when email is not changed")
        void shouldUpdateUserSuccessfully_WhenEmailExists() {
            //Given
            Long id = 1L;
            when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));
            when(userRepository.save(any())).thenReturn(updatedUser);

            //When
            User newlyUpdatedUser = userService.updateUser(expectedUser.getId(), updateUserRequest);

            //Then
            assertEquals(newlyUpdatedUser, updatedUser);
            assertEquals("jane", newlyUpdatedUser.getFirstName());
            assertEquals("jane.doe@email.com", newlyUpdatedUser.getEmail());

            verify(userRepository, times(1)).findById(id);
            verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        @DisplayName("Should successfully updated a user when email is available")
        void shouldSuccessfullyUpdateUser_WhenChangingEmailToAvailableOne() {
            when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
            when(userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), 1L)).thenReturn(false);
            when(userRepository.save(any())).thenReturn(updatedUser);

            User newlyUpdatedUser = userService.updateUser(1L, updateUserRequest);

            assertEquals("jane.doe@email.com", newlyUpdatedUser.getEmail());
            assertEquals("jane", newlyUpdatedUser.getFirstName());

            verify(userRepository, times(1)).findById(1L);
            verify(userRepository, times(1)).existsByEmailAndIdNot("jane.doe@email.com", 1L);
            verify(userRepository, times(1)).save(any(User.class));


        }

        @Test
        @DisplayName("Should Throw ResourceNotFoundException when user ID is not found")
        void ShouldThrowResourceNotFoundException_WhenUserIsNotFound(){
            Long nonExistentId = 999L;
            when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(nonExistentId, updateUserRequest));

            assertEquals("User not found with ID: " + nonExistentId, exception.getMessage());

            verify(userRepository, times(1)).findById(nonExistentId);
            verify(userRepository, never()).save(any(User.class));
        }
    }

    @Test
    @DisplayName("Should Throw EmailAlreadyExistsException when changing to existing email")
    void ShouldThrowEmailAlreadyExistsException_WhenEmailAlreadyExists(){

        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
        when(userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), 1L)).thenReturn(true);

        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.updateUser(1L, updateUserRequest));

        assertEquals("Email is in use", exception.getMessage());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).existsByEmailAndIdNot(updateUserRequest.getEmail(), 1L);
        verify(userRepository, never()).save(any(User.class));


    }

}