package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.mappers.ClassMapper;
import com.devbp.syncspace.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
