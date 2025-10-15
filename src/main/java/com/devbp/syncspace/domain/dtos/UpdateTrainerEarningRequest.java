package com.devbp.syncspace.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTrainerEarningRequest {

    @NotNull
    private Long trainerEarningsId;

    private double earningPercentage;

}
