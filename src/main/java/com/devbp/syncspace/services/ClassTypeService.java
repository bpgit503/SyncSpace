package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateClassTypeRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassTypeRequest;
import com.devbp.syncspace.domain.entities.ClassType;

public interface ClassTypeService {

    //CRUD

    ClassType getAllClassTypes();

    ClassType getClassTypeById();

    //Create search function in REPO that return all similar names
    ClassType getClassTypeByName();

    ClassType addClassType(CreateClassTypeRequest createClassTypeRequest);

    ClassType updateClassType(UpdateClassTypeRequest updateClassTypeRequest);

    void deleteClassTypeBy(Long id);
}
