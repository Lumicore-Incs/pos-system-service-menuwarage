package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.get.OrderDtoGet;

import java.util.List;

public interface OrderService {
    OrderDto delete(Long id);

    List<OrderDtoGet> getAll();

    OrderDtoGet search(Long id);

    OrderDtoGet save(OrderDto order);

    OrderDto update(Long id, OrderDto order);
}
