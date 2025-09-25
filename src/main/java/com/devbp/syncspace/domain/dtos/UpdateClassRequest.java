package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.ClassStatus;
import jakarta.validation.constraints.*;
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
public class UpdateClassRequest {

    @FutureOrPresent(message = "Scheduled date must be in the future or present")
    private LocalDate scheduledDate;

    private LocalTime startTime;

    private LocalTime endTime;

    @Positive
    private int maxCapacity;

    @PositiveOrZero
    private int currentCapacity;

    private ClassStatus classStatus;

    @NotBlank(message = "Add a note stating the reason for the updated")
    private String notes;
}
