package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.TrainerResponseDto;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.domain.mappers.TrainerMapper;
import com.devbp.syncspace.services.Impl.TrainerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainer")
public class TrainerController {

    private final TrainerServiceImpl trainerService;
    private final TrainerMapper trainerMapper;

    @GetMapping
    public ResponseEntity<List<TrainerResponseDto>> getAllTrainers() {
        List<TrainerResponseDto> allTrainers = trainerService.getAllTrainers().stream()
                .map(trainerMapper::toDto)
                .toList();

        return ResponseEntity.ok(allTrainers);

    }

    @PostMapping("/{email}")
    public ResponseEntity<TrainerResponseDto> createTrainer(@PathVariable String email, @Valid  @RequestBody CreateTrainerRequest createTrainerRequest) {

        TrainerResponseDto dto = trainerMapper.toDto(trainerService.addTrainer(email, createTrainerRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
