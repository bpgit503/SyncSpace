package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;


    private String phoneNumber;

    @Past(message = "Birthday must be in the past")
    private LocalDateTime dateOfBirth;

    private String address;

    @NotNull(message = "User type is required")
    private UserType userType;

}
