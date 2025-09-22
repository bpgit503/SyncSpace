package com.devbp.syncspace.services;


import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerRequestDto;
import com.devbp.syncspace.domain.entities.Trainer;

import java.util.List;
/*
method to set toggle trainer isAvaliable on/off
 */
public interface TrainerService {

    List<Trainer> getAllTrainers();

    Trainer getTrainerByEmail(String email);

    Trainer getTrainerById(Long id);

    Trainer addTrainer(String email, CreateTrainerRequest createTrainerRequest);

    Trainer updateTrainer(String email, UpdateTrainerRequestDto updateTrainerRequestDto);

    void deleteTrainerByEmail(String email);
}
