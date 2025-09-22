package com.devbp.syncspace.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerResponseDto {

    private long id;
    private UserResponseDto user;
    private List<String> certifications;
    private List<String> specializations;
    private String contractDetails;
    private double earningsPercentage;
    private double hourlyRate;
    private String bio;
    private int experienceYears;
    private boolean isAvailable;

}
