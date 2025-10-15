package com.devbp.syncspace.domain.dtos;

import com.devbp.syncspace.domain.TrainerPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerEarningsResponseDto {


    private long id;
    private long trainerId;
    private long clazzId;
    private String trainerName;
    private String clazzName;


    private double baseAmount;
    private double earningPercentage;
    private BigDecimal earningAmount;
    private TrainerPaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime calculatedAt;


}
