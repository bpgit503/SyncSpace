package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.TrainerEarnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerEarningsRepository extends JpaRepository<TrainerEarnings, Long> {

    List<TrainerEarnings> findAllByTrainerId(long trainerId);

    @Query("Select te from TrainerEarnings te JOIN Classes c on te.clazz.id = c.id and c.classType.className = :clazzName")
    List<TrainerEarnings> findAllByClazzClassType_ClassName(@Param("clazzName") String clazzClassName);

    Optional<TrainerEarnings> findTrainerEarningsByTrainerIdAndClazzId(long trainerId, long clazzId);
}
