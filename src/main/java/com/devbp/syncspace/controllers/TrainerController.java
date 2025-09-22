package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.CreateTrainerRequest;
import com.devbp.syncspace.domain.dtos.TrainerResponseDto;
import com.devbp.syncspace.domain.dtos.UpdateTrainerRequestDto;
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

    @GetMapping("/{email}")
    public ResponseEntity<TrainerResponseDto> getTrainerByEmail(@PathVariable String email) {
        TrainerResponseDto dto = trainerMapper.toDto(trainerService.getTrainerByEmail(email));

        return ResponseEntity.ok(dto);

    }


    @PostMapping("/{email}")
    public ResponseEntity<TrainerResponseDto> createTrainer(@PathVariable String email, @Valid @RequestBody CreateTrainerRequest createTrainerRequest) {

        TrainerResponseDto dto = trainerMapper.toDto(trainerService.addTrainer(email, createTrainerRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<TrainerResponseDto> updateTrainer(@PathVariable String email, @Valid @RequestBody UpdateTrainerRequestDto updateTrainerRequestDto) {
        TrainerResponseDto dto = trainerMapper.toDto(trainerService.updateTrainer(email, updateTrainerRequestDto));

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable String email) {

        trainerService.deleteTrainerByEmail(email);

        return ResponseEntity.noContent().build();
    }


}
