package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.TrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    @Override
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public Trainer addTrainer(String email, CreateTrainerRequest trainer) {

        User existingUser = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (existingUser.getUserType() != UserType.TRAINER) {
            //TODO should I create custom exception method for this?
            throw new IllegalStateException("User is not of type TRAINER");
        }

        Trainer newTrainer = Trainer.builder()
                .user(existingUser)
                .specializations(trainer.getSpecializations())
                .certifications(trainer.getCertifications())
                .contractDetails(trainer.getContractDetails())
                .earningsPercentage(trainer.getEarningsPercentage())
                .hourlyRate(trainer.getHourlyRate())
                .bio(trainer.getBio())
                .experienceYears(trainer.getExperienceYears())
                .isAvailable(true)
                .build();

        return trainerRepository.save(newTrainer);
    }
}
