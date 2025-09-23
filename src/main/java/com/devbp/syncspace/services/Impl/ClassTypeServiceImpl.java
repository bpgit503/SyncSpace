package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassTypeRequest;
import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.exceptions.ClassTypeAlreadyExistsException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import com.devbp.syncspace.repositories.ClassTypeRepository;
import com.devbp.syncspace.services.ClassTypeService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public ClassType createClassType(CreateClassTypeRequest createClassTypeRequest) {
        if (classTypeRepository.existsClassTypeByClassName(createClassTypeRequest.getClassName())) {
            throw new ClassTypeAlreadyExistsException("Class type already exists with name: " + createClassTypeRequest.getClassName());
        }
        ClassType newClassType = new ClassType();
        newClassType.setClassName(createClassTypeRequest.getClassName());
        newClassType.setDescription(createClassTypeRequest.getDescription());
        newClassType.setDurationMinutes(createClassTypeRequest.getDurationMinutes());
        newClassType.setGroupClass(createClassTypeRequest.isGroupClass());
        newClassType.setMaxCapacity(createClassTypeRequest.getMaxCapacity());
        newClassType.setBasePrice(createClassTypeRequest.getBasePrice());
        newClassType.setActive(createClassTypeRequest.isActive());


        return classTypeRepository.save(newClassType);
    }

    @Transactional
    @Override
    public ClassType updateClassType(long id, UpdateClassTypeRequest updateClassTypeRequest) {
        ClassType existingClassType = getClassTypeById(id);

        if (updateClassTypeRequest.getClassName() != null) {
            if (classTypeRepository.existsClassTypeByClassName(updateClassTypeRequest.getClassName())) {
                throw new ClassTypeAlreadyExistsException("Class type already exists with name: " + updateClassTypeRequest.getClassName());
            } else existingClassType.setClassName(updateClassTypeRequest.getClassName());
        }

        existingClassType.setDescription(updateClassTypeRequest.getDescription());
        existingClassType.setDurationMinutes(updateClassTypeRequest.getDurationMinutes());
        existingClassType.setGroupClass(updateClassTypeRequest.isGroupClass());
        existingClassType.setMaxCapacity(updateClassTypeRequest.getMaxCapacity());
        existingClassType.setBasePrice(updateClassTypeRequest.getBasePrice());
        existingClassType.setActive(updateClassTypeRequest.isActive());

        return classTypeRepository.save(existingClassType);
    }
    @Transactional
    @Override
    public void deleteClassTypeById(Long id) {
        ClassType classType = getClassTypeById(id);
        classTypeRepository.delete(classType);

    }
}
