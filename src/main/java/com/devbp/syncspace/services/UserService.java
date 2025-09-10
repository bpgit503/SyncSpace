package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.CreateUserRequest;
import com.devbp.syncspace.domain.entities.User;

import java.util.List;

//CRUD

/*** TODO: Create the ff
 * get user by id
 * get user by email
 * get list users by first/last name
 * get list user by first and last name
 * Update User
 * Delete User by email and id
 */
public interface UserService {

    List<User> getAllUsers();

    User createUser(CreateUserRequest createUserRequestDto);

    User getUserById(Long id);

    User getUserByEmail(String email);


}
