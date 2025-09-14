package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.UpdateUserRequest;
import com.devbp.syncspace.domain.entities.User;

import java.util.List;

//CRUD

/*** TODO: Create the ff
 * get user by id
 * get user by email
 * get list users by first/last name or email + search function;
 * Update User update userDTO
 * Delete User by id
 * Delete User By Email
 * get user by type
 * activate/deactivate user user
 */
public interface UserService {

    List<User> getAllUsers();

    User createUser(CreateUserRequest createUserRequestDto);

    User getUserById(Long id);

    User getUserByEmail(String email);

    User updateUser(long id, UpdateUserRequest userRequest);

    void deleteUserByEmail(long id);

    void deleteUserByEmail(String Email);

    User activateUserByEmail(String Email);


}
