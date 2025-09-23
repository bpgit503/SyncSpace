package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassTypeRequest;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.ClassTypeRepository;
import com.devbp.syncspace.services.ClassTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassTypeServiceImpl implements ClassTypeService {

    private final ClassTypeRepository classTypeRepository;

    @Override
    public List<ClassType> getAllClassTypes() {
        return classTypeRepository.findAll();
    }

    @Override
    public ClassType getClassTypeById(long id) {
        return classTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class Type not found with id: " + id));
    }

    @Override
    public ClassType getClassTypeByName(String name) {
        return classTypeRepository.findByClassName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Class Type not found with class name: " + name));
    }

    @Override
    public ClassType addClassType(CreateClassTypeRequest createClassTypeRequest) {
        return null;
    }

    @Override
    public ClassType updateClassType(UpdateClassTypeRequest updateClassTypeRequest) {
        return null;
    }

    @Override
    public void deleteClassTypeBy(Long id) {

    }
}
