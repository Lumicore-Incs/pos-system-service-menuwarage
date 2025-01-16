package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.get.UserDtoGet;
import com.example.demo.service.UserService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class AuthController {
    private final UserService userService;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public AuthController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {

            UserDto isUser = this.userService.findUserByName(userDto.getEmail(), userDto.getUserName());
            if (isUser == null) {
                UserDto dto = this.userService.registerUser(userDto);
                return new ResponseEntity<>(dto, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User is exist", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> postLogin(@RequestBody UserDto dto) {
        UserDto user = userService.userLogin(dto);
        Map<String, String> response = new HashMap<>();

        if (user == null) {
            response.put("message", "Wrong details");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            String token = this.jwtTokenGenerator.generateJwtToken(user);
            response.put("user", user.toString());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping("/get_user_info_by_token")
    public ResponseEntity<Object> getUserInfoByToken(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            UserDtoGet userFromJwtToken = this.jwtTokenGenerator.getUserFromJwtToken(authorizationHeader);
            return new ResponseEntity<>(userFromJwtToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            UserDto dto = this.userService.updateUser(userDto, userId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get_all_user")
    public ResponseEntity<Object> getAllUser(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<UserDtoGet> allUsers = this.userService.getAllUser();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search_user/{userId}")
    public ResponseEntity<Object> searchUser(@PathVariable Long userId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            UserDtoGet user = this.userService.getUserById(String.valueOf(userId));
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
