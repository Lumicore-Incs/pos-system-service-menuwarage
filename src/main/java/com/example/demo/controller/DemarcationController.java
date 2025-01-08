package com.example.demo.controller;

import com.example.demo.dto.DemarcationDto;
import com.example.demo.service.DemarcationService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/demarcation")
public class DemarcationController {
    private final JWTTokenGenerator jwtTokenGenerator;
    private final DemarcationService demarcationService;

    @Autowired
    public DemarcationController(JWTTokenGenerator jwtTokenGenerator, DemarcationService demarcationService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.demarcationService = demarcationService;
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody DemarcationDto dto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            DemarcationDto demarcationDto = this.demarcationService.save(dto);
            return new ResponseEntity<>(demarcationDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("search/{id}")
    public ResponseEntity<Object> updateDemarcation(@PathVariable Long id, @RequestBody DemarcationDto demarcationDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            DemarcationDto dto = this.demarcationService.update(demarcationDto, id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<DemarcationDto> allData = this.demarcationService.getAll();
            return new ResponseEntity<>(allData, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> search(@PathVariable Long id, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            Object dto = this.demarcationService.getById(String.valueOf(id));
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
