package com.devbp.syncspace.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTrainerRequest {

    @NotBlank(message = "certifications are required")
    private List<String> certifications;

    @NotBlank(message = "Specializations are required")
    private List<String> specializations;

    @NotBlank(message = "Contract details are required")
    private String contractDetails;

    @NotBlank(message = "Earning percentage is required")
    private double earningsPercentage;
    private double hourlyRate;
    private String bio;
    private int experienceYears = 0;
    private boolean isAvailable = true;

}
