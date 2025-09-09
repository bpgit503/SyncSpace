package com.devbp.syncspace.services;

import com.devbp.syncspace.domain.entities.User;

import java.util.List;

//CRUD

/*** TODO: Create the ff
 * get all users
 * get user by id
 * get users by first/last name
 * get user by first and last name
 * read user by id
 * Update User
 * Delete User by Id & first and last name
 */
public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);


}
