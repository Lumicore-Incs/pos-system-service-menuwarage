package com.example.demo.controller;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.get.NotificationDtoGet;
import com.example.demo.service.NotificationService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("notification")
@RestController
@CrossOrigin
public class NotificationController {
    private final JWTTokenGenerator jwtTokenGenerator;
    private final NotificationService service;

    public NotificationController(JWTTokenGenerator jwtTokenGenerator, NotificationService service) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody NotificationDto order, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            NotificationDto dto = service.save(order);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody NotificationDto order, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            NotificationDto dto = service.update(id, order);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            NotificationDto dto = service.delete(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(@RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            List<NotificationDtoGet> dtoList = service.getAll();
            return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{id}")
    private ResponseEntity<Object> search(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            NotificationDtoGet dto = service.search(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
