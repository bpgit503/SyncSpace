package com.devbp.syncspace.services;


import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.entities.Trainer;

public interface TrainerService {

    Trainer addTrainer(CreateTrainerRequest createTrainerRequest);



}
