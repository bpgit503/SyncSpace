package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ClassTypeResponseDto;
import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassTypeRequest;
import com.devbp.syncspace.domain.mappers.ClassTypeMapper;
import com.devbp.syncspace.services.ClassTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classtype")
@RequiredArgsConstructor
public class ClassTypeController {

    private final ClassTypeService classTypeService;
    private final ClassTypeMapper classTypeMapper;

    @GetMapping
    public ResponseEntity<List<ClassTypeResponseDto>> getAllClassTypes() {
        List<ClassTypeResponseDto> allClassTypes = classTypeService.getAllClassTypes().stream()
                .map(classTypeMapper::toDto)
                .toList();

        return ResponseEntity.ok(allClassTypes);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClassTypeResponseDto> getClassTypeById(@PathVariable Long id) {

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.getClassTypeById(id));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<ClassTypeResponseDto> getClassTypeByName(@PathVariable String name) {

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.getClassTypeByName(name));

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClassTypeResponseDto> createClassType(@Valid @RequestBody CreateClassTypeRequest createClassTypeRequest) {

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.createClassType(createClassTypeRequest));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassTypeResponseDto> updateClassType(@PathVariable long id, @Valid @RequestBody UpdateClassTypeRequest updateClassTypeRequest) {

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.updateClassType(id, updateClassTypeRequest));

        return ResponseEntity.ok(dto);
    }

}
