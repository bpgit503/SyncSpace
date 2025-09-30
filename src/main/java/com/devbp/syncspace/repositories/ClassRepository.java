package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.ClassType;
import com.devbp.syncspace.domain.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {

    @Query(value = "SELECT c.* FROM classes as c " +
            "INNER JOIN class_types as ct " +
            "on c.class_type_id = ct.id " +
            "WHERE ct.is_active = true " +
            "AND c.id =?", nativeQuery = true)
    Optional<Classes> findClassesById_AndIsActive(@Param("id") long id);
}
