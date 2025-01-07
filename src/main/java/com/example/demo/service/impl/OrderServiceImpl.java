package com.example.demo.service.impl;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.get.OrderDtoGet;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final ModelMapperConfig modelMapperConfig;
    private final OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(ModelMapperConfig modelMapperConfig, OrderRepo orderRepo) {
        this.modelMapperConfig = modelMapperConfig;
        this.orderRepo = orderRepo;
    }


    @Override
    public OrderDto delete(Long id) {
        Optional<Order> byId = orderRepo.findById(id);
        if (byId.isPresent()) {
            orderRepo.deleteById(id);
            return entityToDto(byId.get());
        }else {
            return null;
        }
    }

    @Override
    public List<OrderDtoGet> getAll() {
        List<Order> all = orderRepo.findAll();
        List<OrderDtoGet> dtos = new ArrayList<>();
        for (Order order : all) {
            dtos.add(entityToGetDto(order));
        }
        return dtos;
    }

    @Override
    public OrderDtoGet search(Long id) {
        Optional<Order> byId = orderRepo.findById(id);
        return byId.map(this::entityToGetDto).orElse(null);
    }

    @Override
    public OrderDto save(OrderDto order) {
        Order order1 = dtoToEntity(order);
        Order save = orderRepo.save(order1);
        return entityToDto(save);
    }

    @Override
    public OrderDto update(Long id, OrderDto dto) {
        Optional<Order> byId = orderRepo.findById(id);
        if (byId.isPresent()) {
            dto.setId(id);
            Order save = orderRepo.save(dtoToEntity(dto));
            return entityToDto(save);
        }else {
            return null;
        }
    }

    private Order dtoToEntity(OrderDto dto) {
        return modelMapperConfig.modelMapper().map(dto, Order.class);
    }

    private OrderDto entityToDto(Order order) {
        return (order == null) ? null : modelMapperConfig.modelMapper().map(order, OrderDto.class);
    }

    private OrderDtoGet entityToGetDto(Order order) {
        if (order!=null){
            OrderDtoGet map = modelMapperConfig.modelMapper().map(order, OrderDtoGet.class);
            map.setCustomerId(entityToCustomerDto(order.getCustomerId()));
            map.setUserId(entityToUserDto(order.getUserId()));
            return map;
        }
        return null;
    }

    private UserDto entityToUserDto(User userId) {
        return modelMapperConfig.modelMapper().map(userId, UserDto.class);
    }

    private CustomerDto entityToCustomerDto(Customer customerId) {
        return (customerId == null) ? null : modelMapperConfig.modelMapper().map(customerId, CustomerDto.class);
    }
}
