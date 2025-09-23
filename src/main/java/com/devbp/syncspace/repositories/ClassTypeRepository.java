package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.ClassType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassTypeRepository extends JpaRepository<ClassType, Long> {
    Optional<ClassType> findByClassName(String className);
}
