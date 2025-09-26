package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.ClassStatus;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

        return classRepository.save(newClass);
    }

    @Transactional
    @Override
    public Classes updateClass(long id, UpdateClassRequest updateClassRequest) {
        Classes existingClass = getClassById(id);
        String trainerEmail = updateClassRequest.getTrainerEmail();
        Long trainerId = updateClassRequest.getTrainerId();
        String className = updateClassRequest.getClassTypeName();
        Trainer newTrainer = new Trainer();
        ClassType newClassType = new ClassType();

        if (trainerId != null || trainerEmail != null) {
            newTrainer = trainerRepository.findByUser_EmailOrId(trainerEmail, trainerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id : " + trainerId
                            + "or email: " + trainerEmail));
            existingClass.setTrainer(newTrainer);
        }

        if (className != null) {
            newClassType = classTypeRepository.findByClassName(className)
                    .orElseThrow(() -> new ResourceNotFoundException("Class name not found :" + className));
            existingClass.setClassType(newClassType);
        }

        if (updateClassRequest.getScheduledDate() != null) {
            existingClass.setScheduledDate(updateClassRequest.getScheduledDate());
        }

        if (updateClassRequest.getStartTime() != null) {
            existingClass.setStartTime(updateClassRequest.getStartTime());
        }
        if (updateClassRequest.getEndTime() != null) {
            existingClass.setEndTime(updateClassRequest.getEndTime());
        }
        if (updateClassRequest.getMaxCapacity() > 0) {
            existingClass.setMaxCapacity(updateClassRequest.getMaxCapacity());
        }
        if (updateClassRequest.getCurrentCapacity() > 0) {
            existingClass.setCurrentCapacity(updateClassRequest.getCurrentCapacity());
        }

        if (updateClassRequest.getClassStatus() != null) {
            existingClass.setClassStatus(updateClassRequest.getClassStatus());
        }

        existingClass.setNotes(existingClass.getNotes() + "\n" + updateClassRequest.getNotes());


        return classRepository.save(existingClass);
    }

    @Transactional
    @Override
    public Classes cancelClass(long id) {
        Classes existingClass = getClassById(id);
        existingClass.setClassStatus(ClassStatus.CANCELLED);

        return classRepository.save(existingClass);
    }

    @Override
    public void deleteClassById(long id) {

        classRepository.delete(getClassById(id));

    }
}
