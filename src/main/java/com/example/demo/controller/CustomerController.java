package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.service.CustomerService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final JWTTokenGenerator jwtTokenGenerator;

    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody CustomerDto dto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CustomerDto customerDto = this.customerService.save(dto);
            return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody CustomerDto customerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CustomerDto dto = this.customerService.update(customerDto, id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CustomerDto dto = this.customerService.delete(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            List<CustomerDto> allData = this.customerService.getAll();
            return new ResponseEntity<>(allData, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> search(@PathVariable Long id, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CustomerDto dto = this.customerService.getById(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
