package com.devbp.syncspace.config;

import com.devbp.syncspace.domain.UserStatus;
import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.CreateUserRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerRequestDto;
import com.devbp.syncspace.domain.dtos.UpdateUserRequest;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;

import java.time.LocalDate;
import java.util.List;

public class TestConfig {

    public User createUserJohn() {
        return User.builder()
                .id(1L)
                .email("john.doe@email.com")
                .firstName("john")
                .lastName("doe")
                .phoneNumber("012345678")
                .dateOfBirth(LocalDate.parse("1990-02-02"))
                .address("123 street")
                .userType(UserType.CLIENT)
                .build();
    }

    public CreateUserRequest createCreateUserRequestJohn() {
        return CreateUserRequest.builder()
                .email("john.doe@email.com")
                .firstName("john")
                .lastName("doe")
                .phoneNumber("012345678")
                .dateOfBirth(LocalDate.parse("1990-02-02"))
                .address("123 street")
                .userType(UserType.CLIENT)
                .build();
    }

    public UpdateUserRequest createdUpdateUserRequest() {
        return UpdateUserRequest.builder()
                .email("jane.doe@email.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("987654321")
                .dateOfBirth(LocalDate.parse("1995-06-09"))
                .address("321 street")
                .status(UserStatus.INACTIVE)
                .build();
    }

    public User createUpdatedUser() {
        return User.builder()
                .email("jane.doe@email.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("987654321")
                .dateOfBirth(LocalDate.parse("1995-06-09"))
                .address("321 street")
                .status(UserStatus.INACTIVE)
                .build();
    }

    public User createUserOfTypeTrainer() {
        return User.builder()
                .email("MassiveBob@jerky.com")
                .firstName("Beefy")
                .lastName("Bob")
                .phoneNumber("126296969")
                .dateOfBirth(LocalDate.parse("1969-06-09"))
                .address("69 Buff Dabby st")
                .userType(UserType.TRAINER)
                .status(UserStatus.ACTIVE)
                .build();
    }

    public Trainer createTrainer() {
        return Trainer.builder()
                .user(createUserOfTypeTrainer())
                .specializations(List.of("Massive Specializations", "More Massive Specializations"))
                .certifications(List.of("Massive Certification", "Greater Massive Certifications"))
                .contractDetails("Sample Contract")
                .earningsPercentage(0.4)
                .hourlyRate(1)
                .bio("A Massive coach of massive standards who specialty is to get people to become massive in both mind and body")
                .experienceYears(10)
                .isAvailable(true)
                .build();


    }

    public CreateTrainerRequest createCreateTrainerRequest() {

        return CreateTrainerRequest.builder()
                .specializations(List.of("Massive Specializations", "More Massive Specializations"))
                .certifications(List.of("Massive Certification", "Greater Massive Certifications"))
                .contractDetails("Sample Contract")
                .earningsPercentage(0.4)
                .hourlyRate(1)
                .bio("A Massive coach of massive standards who specialty is to get people to become massive in both mind and body")
                .experienceYears(10)
                .isAvailable(true)
                .build();
    }

    public UpdateTrainerRequestDto createUpdateTrainerRequest() {
        return UpdateTrainerRequestDto.builder()
                .specializations(List.of("Updated Massive Specializations", "Updated More Massive Specializations"))
                .certifications(List.of("Updated Massive Certification", "Updated Greater Massive Certifications"))
                .contractDetails("Updated Sample Contract")
                .earningsPercentage(0.5)
                .hourlyRate(1)
                .bio("Updated bio")
                .experienceYears(11)
                .isAvailable(true)
                .build();
    }

    public Trainer updatedTrainer() {
        return Trainer.builder()
                .user(createUserOfTypeTrainer())
                .specializations(List.of("Updated Massive Specializations", "Updated More Massive Specializations"))
                .certifications(List.of("Updated Massive Certification", "Updated Greater Massive Certifications"))
                .contractDetails("Updated Sample Contract")
                .earningsPercentage(0.5)
                .hourlyRate(1)
                .bio("Updated bio")
                .experienceYears(11)
                .isAvailable(true)
                .build();
    }


    public ClassType createClassType() {
        return ClassType.builder()
                .className("GetMassive")
                .description("Come and get absolutely massive with massive Bob")
                .durationMinutes(45)
                .isGroupClass(true)
                .maxCapacity(15)
                .basePrice(10)
                .isActive(true)
                .build();

    }

    public ClassType createClassType2() {
        return ClassType.builder()
                .className("Get Slim")
                .description("Come and slim down with slim Jim")
                .durationMinutes(60)
                .isGroupClass(true)
                .maxCapacity(20)
                .basePrice(12)
                .isActive(true)
                .build();
    }


}
