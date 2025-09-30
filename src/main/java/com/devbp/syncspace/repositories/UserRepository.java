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

    @Query(value = "Select * from users where status = 'ACTIVE' AND user_type = 'CLIENT' and id = ?", nativeQuery = true)
    Optional<User> findActiveClientById(@Param("id") long id);

    @Query("Select u from User u where " +
            "Lower(u.firstName) LIKE lower(concat( '%', :searchTerm, '%')) OR " +
            "LOWER( u.lastName) LIKE LOWER(CONCAT( '%', :searchTerm, '%')) OR " +
            "LOWER( u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchUserByType(@Param("searchTerm") String searchTerm);

    boolean existsByEmailAndIdNot(String email, Long id);

    void deleteUserByEmailAndId(String email, Long id);


    boolean existsByEmailAndId(String email, Long id);
}
