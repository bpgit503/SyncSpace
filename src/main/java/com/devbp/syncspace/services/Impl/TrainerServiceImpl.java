package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    @Override
    public Trainer addTrainer(Trainer trainer) {
        if (!userRepository.existsById(trainer.getUser().getId())) {
            throw new ResourceNotFoundException("User not found with id: " + trainer.getUser().getId());
        }

        if (trainer.getUser().getUserType() != UserType.TRAINER) {
            //TODO should I create custom exception method for this?
            throw new IllegalStateException("User is not of type TRAINER");
        }

        Trainer newTrainer = Trainer.builder()
                .specializations(trainer.getSpecializations())
                .certifications(trainer.getCertifications())
                .contractDetails(trainer.getContractDetails())
                .earningsPercentage(trainer.getEarningsPercentage())
                .hourlyRate(trainer.getHourlyRate())
                .bio(trainer.getBio())
                .experienceYears(trainer.getExperienceYears())
                .isAvailable(trainer.isAvailable())
                .build();

        return trainerRepository.save(newTrainer);
    }
}
