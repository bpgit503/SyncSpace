package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.dtos.CreateClassRequest;
import com.devbp.syncspace.domain.dtos.UpdateClassRequest;
import com.devbp.syncspace.domain.entities.Classes;

import java.util.List;

public interface ClassService {
    /*
    see if patching is better or
    have if null validation for partial updating using put

    get all classes by class type
    get all classes by trainer
    get all classes by class status
    get all classes by date range - day, wek
    get all classes

    when updating class must log in notes ( must not be null)
        and contain as to why it is being updated

        soft delete - class canceled
        hard delete

        #####
        Method to validate that the start time < end time?
     */

    List<Classes> getAllClasses();

    Classes getClassById(long id);

    //Needs Trainer id/email and classtype id/name
    //validate start time and end time s is before e and total time is !> 2hours
    Classes createClass(long classTypeId, long trainerId, CreateClassRequest createClassRequest);

    //create checks when updating trainer and class
    // should not be able to update class to the past
    //make a date log in notes for whenever a change is made
    Classes updateClass(long id, UpdateClassRequest updateClassRequest);

    void cancelClass(long id);


    void deleteClassById(long id);
}
