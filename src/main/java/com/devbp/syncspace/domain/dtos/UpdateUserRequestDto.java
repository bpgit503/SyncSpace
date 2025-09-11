package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequestDto {

    @Email(message = "Email should be valid")
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Past(message = "Birthday should be in the past")
    private LocalDate dateOfBirth;

    private String address;
    private UserStatus status;
}
