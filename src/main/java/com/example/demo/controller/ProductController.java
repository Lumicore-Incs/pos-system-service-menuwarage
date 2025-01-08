package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("product")
@RestController
@CrossOrigin
public class ProductController {
    private final JWTTokenGenerator jwtTokenGenerator;
    private final ProductService service;

    public ProductController(JWTTokenGenerator jwtTokenGenerator, ProductService service) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProductDto productDto, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            ProductDto dto = service.save(productDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProductDto productDto, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            ProductDto dto = service.update(id, productDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            ProductDto dto = service.delete(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(@RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            List<ProductDto> dtoList = service.getAll();
            return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{id}")
    private ResponseEntity<Object> search(@PathVariable Long id, @RequestHeader(name = "Authorization") String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            Object dto = service.search(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
