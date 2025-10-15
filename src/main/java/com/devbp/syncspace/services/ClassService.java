package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateClassRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassRequest;
import com.devbp.syncspace.domain.entities.Classes;

import java.util.List;

public interface ClassService {

    List<Classes> getAllClasses();

    Classes getClassById(long id);

    Classes createClass(long classTypeId, long trainerId, CreateClassRequest createClassRequest);

    Classes updateClass(long id, UpdateClassRequest updateClassRequest);

    Classes cancelClass(long id);

    void deleteClassById(long id);
}
