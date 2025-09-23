package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassTypeRequest;
import com.devbp.syncspace.domain.entities.ClassType;

import java.util.List;

public interface ClassTypeService {

    List<ClassType> getAllClassTypes();

    ClassType getClassTypeById(long id);

    //Create search function in REPO that return all similar names
    ClassType getClassTypeByName(String name);

    ClassType createClassType(CreateClassTypeRequest createClassTypeRequest);

    ClassType updateClassType(long id, UpdateClassTypeRequest updateClassTypeRequest);

    void deleteClassTypeById(Long id);
}
