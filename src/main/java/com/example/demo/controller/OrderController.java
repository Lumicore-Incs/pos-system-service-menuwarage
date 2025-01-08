package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.get.OrderDtoGet;
import com.example.demo.service.OrderService;
import com.example.demo.util.JWTTokenGenerator;
import com.example.demo.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@RestController
@CrossOrigin
public class OrderController {
    private final JWTTokenGenerator jwtTokenGenerator;
        private final OrderService orderService;

    public OrderController(JWTTokenGenerator jwtTokenGenerator, OrderService orderService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody OrderDto order, @RequestHeader(name = "Authorization")String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            OrderDto dto=orderService.save(order);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody OrderDto order, @RequestHeader(name = "Authorization")String token) {
        if (jwtTokenGenerator.validateJwtToken(token)) {
            OrderDto dto=orderService.update(id, order);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, @RequestHeader(name = "Authorization")String token){
        if (jwtTokenGenerator.validateJwtToken(token)) {
            OrderDto dto=orderService.delete(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(@RequestHeader(name = "Authorization")String token){
        if (jwtTokenGenerator.validateJwtToken(token)) {
            List<OrderDtoGet> dtoList=orderService.getAll();
            return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{id}")
    private ResponseEntity<Object> search(@PathVariable Long id, @RequestHeader(name = "Authorization")String token){
        if (jwtTokenGenerator.validateJwtToken(token)) {
            Object dto=orderService.search(id);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
