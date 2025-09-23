package com.devbp.syncspace.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassTypeResponseDto {

    private String className;
    private String description;
    private int durationMinutes = 60;
    private boolean isGroupClass = true;
    private int maxCapacity = 15;
    private double basePrice;
    private boolean isActive = true;
    private LocalDateTime createdAt;

}
