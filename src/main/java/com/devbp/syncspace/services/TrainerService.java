package com.devbp.syncspace.services;


import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.entities.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> getAllTrainers();

    Trainer addTrainer(String email, CreateTrainerRequest createTrainerRequest);



}
