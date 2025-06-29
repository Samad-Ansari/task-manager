package com.taskmanager.service.interfaces;

import com.taskmanager.dto.request.LoginRequest;
import com.taskmanager.dto.request.UserRequest;
import com.taskmanager.model.User;

public interface UserService {
    User createUser(UserRequest request);
    User getUserById(Long id);
    User findByEmail(String email);
    String loginAndGenerateToken(LoginRequest request);
}