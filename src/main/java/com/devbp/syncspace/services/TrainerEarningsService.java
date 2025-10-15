package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateTrainerEarningRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerEarningRequest;
import com.devbp.syncspace.domain.entities.TrainerEarnings;

import java.util.List;

public interface TrainerEarningsService {

    List<TrainerEarnings> getAllTrainerEarnings();

    List<TrainerEarnings> getAllTrainerEarningsByTrainerID(long id);

    List<TrainerEarnings> getAllTrainerEarningsByClassName(String className);

    TrainerEarnings getTrainerEarningById(long id);

    TrainerEarnings createTrainerEarning(CreateTrainerEarningRequest requestDto);

    TrainerEarnings updateTrainerEarningPaymentStatus(long id);

    TrainerEarnings recalculateTrainerEarningPercentage(UpdateTrainerEarningRequest requestDto);

    void deleteTrainerEarningById(long id);
}
