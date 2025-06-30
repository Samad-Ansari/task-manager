package com.taskmanager.util;

import com.taskmanager.dto.response.UserDTO;
import com.taskmanager.model.User;

public class UserMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
