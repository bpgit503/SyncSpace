package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.TrainerResponseDto;
import com.devbp.syncspace.domain.mappers.TrainerMapper;
import com.devbp.syncspace.services.Impl.TrainerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer")
public class TrainerController {

    private final TrainerServiceImpl trainerService;
    private final TrainerMapper trainerMapper;

    @PostMapping
    public ResponseEntity<TrainerResponseDto> createTrainer(@Valid String email, @RequestBody CreateTrainerRequest createTrainerRequest) {

        TrainerResponseDto dto = trainerMapper.toDto(trainerService.addTrainer(email, createTrainerRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
