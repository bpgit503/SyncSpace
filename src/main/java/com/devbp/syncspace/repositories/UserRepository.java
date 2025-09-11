package com.devbp.syncspace.repositories;

import com.devbp.syncspace.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    @Query("Select u from User u where " +
            "Lower(u.firstName) LIKE lower(concat( '%', :searchTerm, '%')) OR " +
            "LOWER( u.lastName) LIKE LOWER(CONCAT( '%', :searchTerm, '%')) OR " +
            "LOWER( u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchUserByType(@Param("searchTerm") String searchTerm);

    boolean existsByEmailAndIdNot(String email, Long id);


}
