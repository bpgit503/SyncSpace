package com.devbp.syncspace.services.Impl;

import com.devbp.syncspace.domain.entities.User;
import com.devbp.syncspace.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
