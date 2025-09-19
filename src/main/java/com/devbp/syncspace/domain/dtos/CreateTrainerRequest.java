package com.devbp.syncspace.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "certifications list cannot be empty")
    private List< @NotBlank(message = "certifications cannot be blank")String> certifications;

    @NotEmpty(message = "Specializations are required")
    private List<String> specializations;

    @NotBlank(message = "Contract Details are required")
    private String contractDetails;

    @NotNull(message = "Earning percentage is required")
    private double earningsPercentage;
    private double hourlyRate;
    private String bio;
    private int experienceYears = 0;
    private boolean isAvailable = true;

}
