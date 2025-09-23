package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ClassTypeResponseDto;
import com.devbp.syncspace.domain.mappers.ClassTypeMapper;
import com.devbp.syncspace.services.ClassTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classtype")
@RequiredArgsConstructor
public class ClassTypeController {

    private final ClassTypeService classTypeService;
    private final ClassTypeMapper classTypeMapper;

    @GetMapping
    public ResponseEntity<List<ClassTypeResponseDto>> getAllClassTypes(){
        List<ClassTypeResponseDto> allClassTypes = classTypeService.getAllClassTypes().stream()
                .map(classTypeMapper::toDto)
                .toList();

        return ResponseEntity.ok(allClassTypes);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClassTypeResponseDto> getClassTypeById(@PathVariable Long id){

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.getClassTypeById(id));

        return ResponseEntity.ok(dto);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<ClassTypeResponseDto> getClassTypeByName(@PathVariable String name){

        ClassTypeResponseDto dto = classTypeMapper.toDto(classTypeService.getClassTypeByName(name));

        return ResponseEntity.ok(dto);
    }

}
