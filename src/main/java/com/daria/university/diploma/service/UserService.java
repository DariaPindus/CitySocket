package com.daria.university.diploma.service;


import com.daria.university.diploma.model.dto.User;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);
    boolean credentialsAreValid(String username, String password);
}
