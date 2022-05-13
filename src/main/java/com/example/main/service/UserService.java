package com.example.main.service;

import com.example.main.model.User;

import java.util.Collection;

public interface UserService {
    User findOne(String email);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String email);
}
