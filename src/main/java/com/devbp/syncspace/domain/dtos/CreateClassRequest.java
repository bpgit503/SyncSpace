package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.ClassStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClassRequest {

    @FutureOrPresent(message = "Scheduled date must be in the future or present")
    @NotNull(message = "Scheduled date is required")
    private LocalDate scheduledDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Max capacity is required")
    @Positive
    private int maxCapacity;

    @PositiveOrZero
    private int currentCapacity;

    @NotNull
    private ClassStatus classStatus;

    private String notes;

}
