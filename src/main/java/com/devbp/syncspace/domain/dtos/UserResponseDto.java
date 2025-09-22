package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime dateOfBirth;
    private String address;
    private UserType userType;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateUserRequest {

        private String email;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private String address;
        private UserStatus status;

    }
}
