package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.TrainerEarnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerEarningsRepository extends JpaRepository<TrainerEarnings, Long> {

    List<TrainerEarnings> findAllByTrainerId(long trainerId);

    List<TrainerEarnings> findAllByClazzClassType_ClassName(String clazzClassName);
}
