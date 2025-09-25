package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.dtos.CreateClassRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassRequest;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.ClassRepository;
import com.devbp.syncspace.services.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    @Override
    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Classes getClassById(long id) {
        return classRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
    }

    @Override
    public Classes createClass(CreateClassRequest createClassRequest) {
        return null;
    }

    @Override
    public Classes updateClass(long id, UpdateClassRequest updateClassRequest) {
        return null;
    }

    @Override
    public void cancelClass(long id) {

    }

    @Override
    public void deleteClassById(long id) {

    }
}
