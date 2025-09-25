package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.ClassResponseDto;
import com.devbp.syncspace.domain.dtos.CreateClassRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassRequest;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.domain.entities.Classes;
import com.devbp.syncspace.domain.entities.Trainer;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.ClassRepository;
import com.devbp.syncspace.repositories.ClassTypeRepository;
import com.devbp.syncspace.repositories.TrainerRepository;
import com.devbp.syncspace.services.ClassService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ClassTypeRepository classTypeRepository;
    private final TrainerRepository trainerRepository;

    @Override
    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Classes getClassById(long id) {
        return classRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
    }

    @Transactional
    @Override
    public Classes createClass(long classTypeId, long trainerId, CreateClassRequest createClassRequest) {
        ClassType existingClassType = classTypeRepository.findById(classTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Class Type not found with id: " + classTypeId));

        Trainer existingTrainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + trainerId));

        Classes newClass = new Classes();
        newClass.setClassType(existingClassType);
        newClass.setTrainer(existingTrainer);
        newClass.setScheduledDate(createClassRequest.getScheduledDate());
        newClass.setStartTime(createClassRequest.getStartTime());
        newClass.setEndTime(createClassRequest.getEndTime());
        newClass.setMaxCapacity(createClassRequest.getMaxCapacity());
        newClass.setCurrentCapacity(createClassRequest.getCurrentCapacity());
        newClass.setNotes(createClassRequest.getNotes());

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
