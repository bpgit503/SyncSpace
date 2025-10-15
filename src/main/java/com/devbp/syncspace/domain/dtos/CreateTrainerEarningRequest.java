package com.devbp.syncspace.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTrainerEarningRequest {

    private long trainerId;

    private long classId;

    private Double baseAmount;

    private Double EarningPercentage;
}
