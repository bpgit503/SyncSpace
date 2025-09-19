package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
                .status(UserStatus.INACTIVE)
                .build();

        this.updatedUser = User.builder()
                .email("jane.doe@email.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("987654321")
                .dateOfBirth(LocalDate.parse("1995-06-09"))
                .address("321 street")
                .status(UserStatus.INACTIVE)
                .build();
    }


    @Nested
    @DisplayName("Create User Tests")
    class CreateUserTests {

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
     *  TODO Learn Argument Captors
     *  TODO create test to verify that all fields have been updated properly
     */

    @Nested
    @DisplayName("Update User Tests")
    class UpdateUserTests {

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
        void ShouldThrowResourceNotFoundException_WhenUserIsNotFound() {
            Long nonExistentId = 999L;
            when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(nonExistentId, updateUserRequest));

            assertEquals("User not found with id: " + nonExistentId, exception.getMessage());

            verify(userRepository, times(1)).findById(nonExistentId);
            verify(userRepository, never()).save(any(User.class));
        }

        @Test
        @DisplayName("Should Throw EmailAlreadyExistsException when changing to existing email")
        void ShouldThrowEmailAlreadyExistsException_WhenEmailAlreadyExists() {

            when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));
            when(userRepository.existsByEmailAndIdNot(updateUserRequest.getEmail(), 1L)).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.updateUser(1L, updateUserRequest));

            assertEquals("Email is in use", exception.getMessage());

            verify(userRepository, times(1)).findById(1L);
            verify(userRepository, times(1)).existsByEmailAndIdNot(updateUserRequest.getEmail(), 1L);
            verify(userRepository, never()).save(any(User.class));

        }
    }

    @Nested
    @DisplayName("Find User By ID Tests")
    class FindUserByIdTests {
        @Test
        @DisplayName("Should successfully return a user when ID exists")
        void ShouldSuccessfullyReturnUser() {
            Long id = 1L;
            when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

            User foundUser = userService.findUserById(id);

            assertNotNull(foundUser);
            assertEquals(expectedUser.getId(), foundUser.getId());
            assertEquals(expectedUser.getFirstName(), foundUser.getFirstName());
            assertEquals(expectedUser.getEmail(), foundUser.getEmail());

            verify(userRepository, times(1)).findById(1L);

        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when user does not exist")
        void shouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            Long nonExistentUserId = 999L;
            when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(nonExistentUserId));

            assertEquals("User not found with id: " + nonExistentUserId, exception.getMessage());

            verify(userRepository, times(1)).findById(nonExistentUserId);
        }

    }


    @Nested
    @DisplayName("Find User By Email Tests")
    class FindUserByEmailTests {

        @Test
        @DisplayName("Should successfully return a user when email exists")
        void shouldSuccessfullyReturnUser_WhenEmailExists() {

            when(userRepository.findUserByEmail(expectedUser.getEmail())).thenReturn(Optional.of(expectedUser));

            User foundUser = userService.findUserByEmail(expectedUser.getEmail());

            assertNotNull(foundUser);
            assertEquals(expectedUser.getEmail(), foundUser.getEmail());
            assertEquals(expectedUser.getId(), foundUser.getId());
            assertEquals(expectedUser.getFirstName(), foundUser.getFirstName());

            verify(userRepository, times(1)).findUserByEmail(expectedUser.getEmail());

        }

        @Test
        @DisplayName("Should throw EmailNotFoundException when user does not exist")
        void ShouldThrowResourceNotFoundException_WhenUserDoesNotExist() {
            String email = "NonExistent@email.com";

            when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

            EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () -> userService.findUserByEmail(email));


            assertEquals("User not found with email : " + email, exception.getMessage());

            verify(userRepository, times(1)).findUserByEmail(email);

        }

    }


    @Nested
    @DisplayName("Should Delete User By Email or Id")
    class ShouldDeleteUserByEmailOrId {

        @Test
        @DisplayName("Should successfully Delete user by Id")
        void shouldSuccessfullyDeleteUser_WhenExistsById() {
            when(userRepository.findById(expectedUser.getId())).thenReturn(Optional.of(expectedUser));

            userService.deleteUserById(expectedUser.getId());

            verify(userRepository, times(1)).delete(expectedUser);
        }

        @Test
        @DisplayName("Should successfully Delete user by Email")
        void shouldSuccessfullyDeleteUser_WhenExistsByEmail() {

            when(userRepository.findUserByEmail(expectedUser.getEmail())).thenReturn(Optional.of(expectedUser));

            userService.deleteUserByEmail(expectedUser.getEmail());

            verify(userRepository, times(1)).delete(expectedUser);

        }


    }

    @Test
    @DisplayName("Should successfully change User Status to Active when user exists")
    void shouldSuccessfullyUpdateUserStatusToActive_WhenUserExists() {

        when(userRepository.findUserByEmail(updatedUser.getEmail())).thenReturn(Optional.of(updatedUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User newylUpdatedUser = userService.activateUserByEmail(updatedUser.getEmail());

        assertNotNull(newylUpdatedUser);
        assertEquals(updatedUser.getEmail(), newylUpdatedUser.getEmail());
        assertEquals(UserStatus.ACTIVE, newylUpdatedUser.getStatus());

        verify(userRepository, times(1)).findUserByEmail(updatedUser.getEmail());

    }

    @Test
    @DisplayName("Should successfully change User Status to Inactive when user exists")
    void shouldSuccessfullyUpdateUserStatusToInactive_WhenUserExists() {

        when(userRepository.findUserByEmail(expectedUser.getEmail())).thenReturn(Optional.of(expectedUser));
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        User newylUpdatedUser = userService.deactivateUserByEmail(expectedUser.getEmail());

        assertNotNull(newylUpdatedUser);
        assertEquals(expectedUser.getEmail(), newylUpdatedUser.getEmail());
        assertEquals(UserStatus.INACTIVE, newylUpdatedUser.getStatus());

        verify(userRepository, times(1)).findUserByEmail(expectedUser.getEmail());

    }


}