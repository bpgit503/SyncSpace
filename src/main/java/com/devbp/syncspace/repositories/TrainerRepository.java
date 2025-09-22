package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByUser_Email(String email);

    @Query(value = "Select t.*, u.email FROM trainers t JOIN users u ON u.id=t.user_id where u.email =?", nativeQuery = true)
    Trainer findTrainerByEmail(@Param("email") String email);
}

