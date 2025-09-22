package com.devbp.syncspace.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTrainerRequestDto {


    private List<String> certifications;
    private List<String> specializations;
    private String contractDetails;

    @PositiveOrZero
    private double earningsPercentage;
    @PositiveOrZero
    private double hourlyRate;
    private String bio;

    @PositiveOrZero
    private int experienceYears;

    private boolean isAvailable;

}
