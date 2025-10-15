package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.dtos.CreateClassRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassRequest;
import com.devbp.syncspace.domain.mappers.ClassMapper;
import com.devbp.syncspace.services.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final ClassMapper classMapper;

    @GetMapping
    public ResponseEntity<List<ClassResponseDto>> getAllClassTypes() {
        List<ClassResponseDto> allClasses = classService.getAllClasses().stream()
                .map(classMapper::toDto)
                .toList();

        return ResponseEntity.ok(allClasses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDto> getClassTypeById(@PathVariable Long id) {

        ClassResponseDto dto = classMapper.toDto(classService.getClassById(id));

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/{classId}/{trainerId}")
    public ResponseEntity<ClassResponseDto> createClass(@PathVariable Long classId, @PathVariable Long trainerId, @Valid
    @RequestBody CreateClassRequest createClassRequest) {

        ClassResponseDto dto = classMapper.toDto(classService.createClass(classId, trainerId, createClassRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDto> updateClass(@PathVariable Long id, @Valid @RequestBody UpdateClassRequest updateClassRequest) {

        ClassResponseDto dto = classMapper.toDto(classService.updateClass(id, updateClassRequest));

        return ResponseEntity.ok(dto);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClassResponseDto> updateClassStatusToCancelled(@PathVariable Long id) {

        ClassResponseDto dto = classMapper.toDto(classService.cancelClass(id));

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassResponseDto> deleteClassById(@PathVariable Long id) {

        classService.deleteClassById(id);

        return ResponseEntity.noContent().build();

    }


}
