package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.ClassStatus;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.domain.entities.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassResponseDto {

    private long id;
    private ClassType classType;
    private Trainer trainer;
    private LocalDate scheduledDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private int maxCapacity;
    private int currentCapacity;
    private ClassStatus classStatus;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
