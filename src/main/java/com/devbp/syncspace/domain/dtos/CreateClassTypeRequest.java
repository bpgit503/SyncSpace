package com.devbp.syncspace.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClassTypeRequest {

    @NotBlank(message = "Class name is required")
    private String className;

    private String description;

    @Positive
    @NotNull(message = "Duration of class in minuets is required")
    private int durationMinutes = 60;

    @NotNull(message = "Group class setting is required (True/False)") //** find better wording
    private boolean isGroupClass = true;

    @NotNull(message = "Max capacity is required")
    @Positive
    private int maxCapacity = 15;

    @NotNull(message = "Base price is required")
    @Positive
    private double basePrice;

    @NotNull(message = "Set Class activity to true or false")
    private boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
