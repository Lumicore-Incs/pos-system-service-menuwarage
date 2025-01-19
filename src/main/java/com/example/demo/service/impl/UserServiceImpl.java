package com.example.demo.service.impl;

import com.example.demo.dto.DemarcationDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.get.UserDtoGet;
import com.example.demo.model.Demarcation;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ModelMapperConfig modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapperConfig modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto registerUser(UserDto user) {
        User user1 = this.dtoToEntity(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        User save = this.userRepo.save(user1);
        return entityToDto(save);
    }

    @Override
    public UserDto userLogin(UserDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> userNames = userRepo.findByUserName(dto.getUserName());
        for (User name : userNames) {
            boolean isPasswordMatches = passwordEncoder.matches(dto.getPassword(), name.getPassword());
            if (isPasswordMatches) {
                return entityToDto(name);
            }
        }
        return null;
    }

    @Override
    public UserDtoGet getUserById(String id) {
        User byId = userRepo.findUserById(Long.valueOf(id));
        return entityToDtoGet(byId);
    }

    @Override
    public List<UserDtoGet> getAllUser() {
        List<User> all = userRepo.findAll();
        List<UserDtoGet> allUsers = new ArrayList<>();
        for (User user : all) {
            UserDtoGet userDto = entityToDtoGet(user);
            allUsers.add(userDto);
        }
        return allUsers;
    }

    @Override
    public UserDto findUserByName(String email, String userName) {
        User userDetails = userRepo.findUserByEmailAndUserName(email, userName);
        return entityToDto(userDetails);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User byId = userRepo.findUserById(userId);
        if (byId==null){
            return null;
        }else {
            userDto.setId(byId.getId());
            User user = dtoToEntity(userDto);
            User save = userRepo.save(user);
            return entityToDto(save);
        }
    }


    private User dtoToEntity(UserDto dto) {
        return modelMapper.modelMapper().map(dto, User.class);
    }

    private UserDto entityToDto(User user) {
        return (user == null) ? null : modelMapper.modelMapper().map(user, UserDto.class);
    }

    public UserDtoGet entityToDtoGet(User user) {
        if (user==null){
            return null;
        }else {
            UserDtoGet map = modelMapper.modelMapper().map(user, UserDtoGet.class);
            map.setDemarcationDto(entityToDemarcationDto(user.getDemarcationId()));
            return map;
        }
    }

    private DemarcationDto entityToDemarcationDto(Demarcation demarcation) {
        return (demarcation == null) ? null : modelMapper.modelMapper().map(demarcation, DemarcationDto.class);
    }
}
