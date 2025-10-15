package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.CreateTrainerEarningRequest;
import com.devbp.syncspace.domain.dtos.TrainerEarningsResponseDto;
import com.devbp.syncspace.domain.dtos.UpdateTrainerEarningRequest;
import com.devbp.syncspace.domain.mappers.TrainerEarningMapper;
import com.devbp.syncspace.services.TrainerEarningsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/earnings")
@RequiredArgsConstructor
public class TrainerEarningsController {

    private final TrainerEarningsService trainerEarningsService;
    private final TrainerEarningMapper trainerEarningMapper;

    @GetMapping
    public ResponseEntity<List<TrainerEarningsResponseDto>> getAllTrainersEarnings() {
        List<TrainerEarningsResponseDto> allTrainerEarnings = trainerEarningsService.getAllTrainerEarnings().stream()
                .map(trainerEarningMapper::toDto)
                .toList();

        return ResponseEntity.ok(allTrainerEarnings);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<List<TrainerEarningsResponseDto>> getAllTrainerEarningsById(@PathVariable long id) {
        List<TrainerEarningsResponseDto> allTrainersEarnings = trainerEarningsService.getAllTrainerEarningsByTrainerID(id).stream()
                .map(trainerEarningMapper::toDto)
                .toList();

        return ResponseEntity.ok(allTrainersEarnings);
    }

    @GetMapping("/classname/{className}")
    public ResponseEntity<List<TrainerEarningsResponseDto>> getAllTrainerEarningsByClassName(@PathVariable String className) {
        List<TrainerEarningsResponseDto> allTrainersEarnings = trainerEarningsService.getAllTrainerEarningsByClassName(className).stream()
                .map(trainerEarningMapper::toDto)
                .toList();

        return ResponseEntity.ok(allTrainersEarnings);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TrainerEarningsResponseDto> getTrainerEarningById(@PathVariable long id) {
        TrainerEarningsResponseDto dto = trainerEarningMapper.toDto(trainerEarningsService.getTrainerEarningById(id));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TrainerEarningsResponseDto> createTrainerEarning(@Valid @RequestBody CreateTrainerEarningRequest createDto) {

        TrainerEarningsResponseDto dto = trainerEarningMapper.toDto(trainerEarningsService.createTrainerEarning(createDto));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrainerEarningsResponseDto> updateTrainerEarningPaymentStatusToPaid(@PathVariable long id) {

        TrainerEarningsResponseDto dto = trainerEarningMapper.toDto(trainerEarningsService.updateTrainerEarningPaymentStatus(id));

        return ResponseEntity.ok(dto);
    }

    @PatchMapping()
    public ResponseEntity<TrainerEarningsResponseDto> updateTrainerEarningPercentage(@Valid @RequestBody UpdateTrainerEarningRequest requestDto) {

        TrainerEarningsResponseDto dto = trainerEarningMapper.toDto(trainerEarningsService.recalculateTrainerEarningPercentage(requestDto));

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainerEarning(@PathVariable long id) {

        trainerEarningsService.deleteTrainerEarningById(id);

        return ResponseEntity.noContent().build();
    }

}
