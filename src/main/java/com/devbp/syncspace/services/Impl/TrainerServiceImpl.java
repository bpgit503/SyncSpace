package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.UserType;
import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.UpdateTrainerRequestDto;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.exceptions.InvalidUserTypeException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.repositories.UserRepository;
import com.devbp.syncspace.services.TrainerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Trainer getTrainerByEmail(String email) {
        return trainerRepository.findByUser_Email(email)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with email: " + email));
    }

    @Override
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));
    }

    @Transactional
    @Override
    public Trainer addTrainer(String email, CreateTrainerRequest trainer) {
        //TODO create a check to see if new Trainer has been already added before

        User existingUser = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (existingUser.getUserType() != UserType.TRAINER) {
            throw new InvalidUserTypeException("User is not of type TRAINER");
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

    @Transactional
    @Override
    public Trainer updateTrainer(String email, UpdateTrainerRequestDto updateTrainerRequestDto) {
        Trainer existingtrainer = getTrainerByEmail(email);

        existingtrainer.setSpecializations(updateTrainerRequestDto.getSpecializations());
        existingtrainer.setCertifications(updateTrainerRequestDto.getCertifications());
        existingtrainer.setContractDetails(updateTrainerRequestDto.getContractDetails());
        existingtrainer.setEarningsPercentage(updateTrainerRequestDto.getEarningsPercentage());
        existingtrainer.setHourlyRate(updateTrainerRequestDto.getHourlyRate());
        existingtrainer.setBio(updateTrainerRequestDto.getBio());
        existingtrainer.setExperienceYears(updateTrainerRequestDto.getExperienceYears());
        existingtrainer.setAvailable(updateTrainerRequestDto.isAvailable());

        return trainerRepository.save(existingtrainer);
    }

    @Override
    public void deleteTrainerByEmail(String email) {

        trainerRepository.delete(getTrainerByEmail(email));
    }
}
