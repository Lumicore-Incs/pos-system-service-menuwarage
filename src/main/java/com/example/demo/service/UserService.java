package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.get.UserDtoGet;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto user);
    UserDto userLogin(UserDto dto);
    UserDtoGet getUserById(String id);
    List<UserDtoGet> getAllUser();
    UserDto findUserByName(String email, String userName);
    UserDto updateUser(UserDto userDto, Long userId);
}
